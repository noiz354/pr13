package com.tokopedia.track;

// Treck.getInstance().registerImplementation("GTM", SuicideGTM.class)
// mapOf("MOENGAGE", listOf(MOENGAGESuicide.class, "isExistingUser")

// configuration
// enable or not
// Treck.getInstance().get("GTM").sendEvent(Map<String,Object>)
// Treck.getInstance().get("MOENGAGE").get("isExistingUser")
// Treck.getInstance().get("MOENGAGE").set("isExistingUser", Object)
// Treck.getInstance().create(MOENGAGESuicide.class).saveAndBuild("MOENGAGE");
// Treck.get("MOENGAGE").isExistingUser();

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Preconditions;

import com.google.android.gms.common.util.ProcessUtils;
import com.tokopedia.track.components.ContextAnalytics;
import com.tokopedia.track.components.GTMSuicide;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Moengage
 * @Type("Moengage")
 * class MOENGAGESuicide{
 *
 *   public void isExistingUser(){}
 * }
 *
 @Type("GTM")
  * class SuicideGTM{
  *
  *   public void sendEvent(Map<String,Object>){}
  * }
 *
 * join with another config. RolloutManager.
 */
public class TrackApp {
    private static final Object LOCK = new Object();

    private static final Object LOCK2 = new Object();

    @GuardedBy("LOCK")
    static final Map<String, TypedValue<? extends ContextAnalytics>> INSTANCES = new ArrayMap<>();

    @SuppressLint("StaticFieldLeak")
    @GuardedBy("LOCK2")
    private static TrackApp trackApp;

    private static final Class<?>[] CONTEXT_ANALYTICS_CONSTRUCTOR_SIGNATURE =
            new Class[]{Context.class};

    public volatile Context context;

    private final AtomicBoolean deleted = new AtomicBoolean();

    /**
     *
     *
     * @param context Application Context
     */
    private TrackApp(Context context){
        this.context = context;
    }

    /**
     * remove when necessary
     */
    public TrackApp(){

    }

    /**
     *
     * @return
     */
    @Nullable
    public static TrackApp getInstance(){
        synchronized (LOCK2){
            if(trackApp == null){
                throw new IllegalStateException(
                        "Default TrackApp is not initialized in this "
                        +"process "
                        + ProcessUtils.getMyProcessName()
                        + ". make sure to call "
                        + "TrackApp.initTrackApp(Context) first."
                );
            }
            return trackApp;
        }
    }

    /**
     * this method needs to be called before other methods.
     * otherwise it will break other methods.
     * @param context
     * @return
     */
    public static TrackApp initTrackApp(Context context){
        synchronized (LOCK2){
            Context applicationContext;
            if(context.getApplicationContext()==null){
                applicationContext = context;
            }else{
                applicationContext = context.getApplicationContext();
            }

           if(trackApp==null){
               trackApp = new TrackApp(applicationContext);
           }
           return trackApp;
        }
    }

    private void checkNotDeleted(){
        Preconditions.checkState(!deleted.get(), "TrackApp was deleted");
    }

    public void registerImplementation(String TAG, Class<? extends ContextAnalytics> className){
        // determine if trackapp is initialized or not ??
        checkNotDeleted();

        // create class using reflection
        getInstance();

        // register to map
        synchronized (LOCK) {
            INSTANCES.put(TAG, createContextAnalytics(className));
        }
    }

    public void initializeAllApis(){
        checkNotDeleted();

        getInstance();

        synchronized (LOCK){
            // get TrackApp object
            for (Iterator<String> iterator = INSTANCES.keySet().iterator(); iterator.hasNext();){
                String key = iterator.next();
                ContextAnalytics analytics = trackApp.getValue(key);
                analytics.initialize();
            }
        }
    }

    private <T extends ContextAnalytics> TypedValue<? extends ContextAnalytics> createContextAnalytics(Class<? extends ContextAnalytics> className){
        if(className != null){
            try{
                ClassLoader classLoader = context.getClassLoader();
                Class<? extends ContextAnalytics> contextAnalyticsClass
                        = classLoader.loadClass(className.getName()).asSubclass(ContextAnalytics.class);

                Constructor<? extends ContextAnalytics> constructor;
                Object[] constructorArgs = null;
                try{
                    constructor = contextAnalyticsClass.getConstructor(CONTEXT_ANALYTICS_CONSTRUCTOR_SIGNATURE);
                    constructorArgs  = new Object[]{context};
                }catch(NoSuchMethodException e){
                    try{
                        constructor =  contextAnalyticsClass.getConstructor();
                    }catch(NoSuchMethodException e1){
                        e1.initCause(e);
                        throw new IllegalStateException(className.getName()
                                + ": Error creating LayoutManager " + className, e1);
                    }
                }
                constructor.setAccessible(true);
                return new TypedValue(className, constructor.newInstance(constructorArgs));
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(className.getName()
                        + ": Unable to find LayoutManager " + className, e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(className.getName()
                        + ": Could not instantiate the LayoutManager: " + className, e);
            } catch (InstantiationException e) {
                throw new IllegalStateException(className.getName()
                        + ": Could not instantiate the LayoutManager: " + className, e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(className.getName()
                        + ": Cannot access non-public constructor " + className, e);
            } catch (ClassCastException e) {
                throw new IllegalStateException(className.getName()
                        + ": Class is not a LayoutManager " + className, e);
            }
        }
        return null;
    }

    public void delete(){
        // remove every instance of object hold in here
    }

    public ContextAnalytics getValue(String TAG){
//        Map<String, TypedValue<? extends ContextAnalytics>>
//                result = new HashMap<>();
//        result.put("GTM", new TypedValue(GTMSuicide.class, new GTMSuicide()));
//        ContextAnalytics typedValue = result.get(TAG).value;
//
//        return  type.cast(typedValue);

        TypedValue<? extends ContextAnalytics> typedValue = INSTANCES.get(TAG);
        ContextAnalytics value = typedValue.value;
        return typedValue.type.cast(value);
    }

    public void addBatch(String TAG, String methodName){
        // certain size
        // timer
    }

    static class TypedValue<T> {
        public final Class<T> type;
        public final T value;
        TypedValue(Class<T> type, T value) {
            this.type = type;
            this.value = value;
        }
    }


}
