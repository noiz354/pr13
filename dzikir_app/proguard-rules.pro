# Obfuscation parameters:
-dontwarn java.nio.file.Files
-dontwarn java.nio.file.Path
-dontwarn java.nio.file.OpenOption
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

-useuniqueclassmembernames
-keepattributes Signature, SourceFile, LineNumberTable, JavascriptInterface
-allowaccessmodification

-dontwarn com.comscore.instrumentation.**
-dontwarn twitter4j.**
-dontwarn oauth.signpost.signature.**
-dontwarn com.squareup.picasso.**
-dontwarn com.facebook.**
-dontwarn com.tokopedia.tkpd.analytics.**
-dontwarn okio.**
-dontwarn com.drew.metadata.xmp.**
-dontwarn com.viewpagerindicator.LinePageIndicator
-dontwarn butterknife.internal.**
-dontwarn com.raizlabs.**

-keep class com.android.support.** { *; }
-keepclasseswithmembers class * {
    @com.android.support.** *;
}
-keepclassmembers class * {
    @com.android.support.** *;
}

-keep class com.google.android.gms.** { *; }
-keepclasseswithmembers class * {
    @com.google.android.gms.** *;
}
-keepclassmembers class * {
    @com.google.android.gms.** *;
}

-keep class com.viewpagerindicator.** { *; }
-keepclasseswithmembers class * {
    @com.viewpagerindicator.** *;
}
-keepclassmembers class * {
    @com.viewpagerindicator.** *;
}

-keep class com.squareup.picasso.** { *; }
-keepclasseswithmembers class * {
    @com.squareup.picasso.** *;
}
-keepclassmembers class * {
    @com.squareup.picasso.** *;
}

-keep class com.sociomantic.sociomanticandroidsdk.** { *; }
-keepclasseswithmembers class * {
    @com.sociomantic.sociomanticandroidsdk.** *;
}
-keepclassmembers class * {
    @com.sociomantic.sociomanticandroidsdk.** *;
}

-keep class com.mcxiaoke.volley.** { *; }
-keepclasseswithmembers class * {
    @com.mcxiaoke.volley.** *;
}
-keepclassmembers class * {
    @com.mcxiaoke.volley.** *;
}

-keep class info.vividcode.android.zxing.** { *; }
-keepclasseswithmembers class * {
    @info.vividcode.android.zxing.** *;
}
-keepclassmembers class * {
    @info.vividcode.android.zxing.** *;
}

-keep class com.github.asne.facebook.** { *; }
-keepclasseswithmembers class * {
    @com.github.asne.facebook.** *;
}
-keepclassmembers class * {
    @com.github.asne.facebook.** *;
}

-keep class com.newrelic.agent.android.** { *; }
-keepclasseswithmembers class * {
    @com.newrelic.agent.android.** *;
}
-keepclassmembers class * {
    @com.newrelic.agent.android.** *;
}

-keep class com.crashlytics.sdk.android.** { *; }
-keepclasseswithmembers class * {
    @com.crashlytics.sdk.android.** *;
}
-keepclassmembers class * {
    @com.crashlytics.sdk.android.** *;
}
-keep class com.facebook.** { *; }
-keepclasseswithmembers class * {
    @com.facebook.** *;
}
-keepclassmembers class * {
    @com.facebook.** *;
}

-keep class id.co.veritrans.android.api.**{ *;}
-keepclassmembers class id.co.veritrans.android.api.**{
 @id.co.veritrans.android.api.** *;
}
-keepclasseswithmembers class id.co.veritrans.android.api.**{
 @id.co.veritrans.android.api.** *;
 }

#Localytics setting
-keep class com.localytics.android.** { *; }

-keep class com.google.android.gms.ads.** { *; }

# Required for Google Play Services (see http://developer.android.com/google/play-services/setup.html)
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}
-keep class com.google.android.gms.gcm.**{ *; }


-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

#---------- End of Localytics

-keepname class com.android.volley.*

-keep class com.tokopedia.tkpd.analytics.** { *; }
-keepclasseswithmembers class * {
    @com.tokopedia.tkpd.analytics.** *;
}
-keepclassmembers class * {
    @com.tokopedia.tkpd.analytics.** *;
}

-keep class android.support.design.widget.** { *; }
-keep interface android.support.design.widget.** { *; }

-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

# support-v4
-dontwarn android.support.v4.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }

# support-v7
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

# http://stackoverflow.com/questions/29679177/cardview-shadow-not-appearing-in-lollipop-after-obfuscate-with-proguard/29698051
-keep class android.support.v7.widget.RoundRectDrawable { *; }

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

#--------- Parecler exception
#---- To configure Proguard, add the following lines to your proguard configuration file.
#---- These will keep files related to the Parcels utilty class and the Parcelable CREATOR instance:
# Parcel library
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class org.parceler.Parceler$$Parcels

-keep class twitter4j.** { *; }
-keepclasseswithmembers class * {
    @twitter4j.** *;
}
-keepclassmembers class * {
    @twitter4j.** *;
}

# this is butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#recharge
-keep class com.tokopedia.core.recharge.model.** { *; }


-keepattributes *Annotation*,EnclosingMethod,Signature

-dontwarn com.squareup.okhttp.**
# this is butterknife

# this is retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
# this is retrofit

# Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

-dontwarn sun.misc.Unsafe

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

-keep class com.drew.** {*;}
-keep interface com.drew.** {*;}
-keep enum com.drew.** {*;}

-keepattributes *Annotation*

-keepattributes Signature

-keepattributes InnerClasses

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
  @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
  @butterknife.* <methods>;
}

-keep class com.activeandroid.**{ *; }

-keep class org.parceler.**{ *; }

-keep class com.bumptech.glide.**{ *; }

-keep public class * implements com.bumptech.glide.module.GlideModule

-keep class com.tokopedia.core.recharge.**{ *; }

-keep class com.tokopedia.core.discovery.model.**{ *; }
-keep class com.tokopedia.core.dynamicfilter.model.** { *; }
-keep class com.tokopedia.core.dynamicfilter.adapter.** { *; }

# add exception for dbflow
-keep class * extends com.raizlabs.android.dbflow.config.DatabaseHolder { *; }
-keep class com.tokopedia.core.database.model.** { *; }
-keep class com.tokopedia.core.database.recharge.** { *; }



#selling
-keep class com.tokopedia.seller.selling.model.** { *; }

-keep class com.tokopedia.tkpd.fcm.** { *; }

-keep class com.airbnb.deeplinkdispatch.** { *; }
-keepclasseswithmembers class * {
     @com.airbnb.deeplinkdispatch.DeepLink <methods>;
}

-keep class com.tokopedia.ride.common.animator.RouteMapAnimator
-keepclasseswithmembers class com.tokopedia.ride.common.animator.RouteMapAnimator {
   public <methods>;
   public <fields>;
}

# Parceler configuration
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }
-keep class org.parceler.Parceler$$Parcels

-keep class com.tokopedia.core.home.model.** { *; }

-keep class com.tokopedia.core.util.GlobalConfig
-keep class com.tokopedia.tkpdpdp.fragment.ProductDetailFragment
-keep class com.tokopedia.core.network.entity.** { *; }
-dontwarn com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar

-keep class com.beloo.widget.* { *; }
 -keep interface com.beloo.widget.io.**
 -keep enum com.beloo.widget.**
-dontwarn com.beloo.widget.**


# -- Feeds --
-keep class com.tkpdfeed.feeds.** { *; }
# -- End of Feeds --

# Fingerprint
-keep class com.tokopedia.core.analytics.fingerprint.domain.model.** { *; }

-dontwarn android.content.pm.PackageManager
-dontwarn com.crashlytics.android.answers.shim.**
-dontwarn com.nimbusds.jwt.SignedJWT
-dontwarn com.nimbusds.jwt.JWTClaimsSet

-dontwarn com.appsflyer.**
-keep public class com.google.firebase.iid.FirebaseInstanceId {
    public *;
}
-keep public class com.google.android.gms.iid.InstanceID {
    public *;
}

-keep public class com.tokopedia.design.component.BottomNavigation { *; }
-keep public class android.support.design.widget.BottomNavigationView { *; }
-keep public class android.support.design.internal.BottomNavigationMenuView { *; }
-keep public class android.support.design.internal.BottomNavigationPresenter { *; }
-keep public class android.support.design.internal.BottomNavigationItemView { *; }
#-keep public class com.tokopedia.design.text.SpinnerCounterInputView { *; }
-keep class * extends com.tokopedia.design.base.BaseCustomView { *; }

-dontwarn android.net.http.**
-dontwarn org.apache.http.impl.auth.NegotiateScheme
-dontwarn com.android.internal.http.multipart.MultipartEntity
-dontwarn org.xbill.DNS.spi.DNSJavaNameServiceDescriptor

#events
-keep class com.tokopedia.events.domain.model.** { *; }
-keep class com.tokopedia.events.view.viewmodel.** { *; }
-keep class com.tokopedia.core.network.exception.model.** { *; }
-keep class com.tokopedia.events.data.entity.response.** { *; }

#image search
-keep class com.tokopedia.discovery.imagesearch.domain.model.** { *; }

#Sendbird
-dontwarn com.sendbird.android.shadow.**

-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

-dontwarn sun.misc.Unsafe
-dontwarn com.google.common.collect.MinMaxPriorityQueue
-dontwarn com.google.common.util.concurrent.FuturesGetChecked**
-dontwarn javax.lang.model.element.Modifier

-dontwarn afu.org.checkerframework.**
-dontwarn org.checkerframework.**

-dontwarn org.xmlpull.v1.**

# Proguard rules that are applied to your test apk/code.
-ignorewarnings

-keepattributes *Annotation*

-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.JavaWriter
# Uncomment this if you use Mockito
-dontwarn org.mockito.**

-keepattributes Annotation
-keep class net.sqlcipher.**
-keep class com.raizlabs.android.dbflow.** { *; }
-keep class com.test.dbflow.dbflowproguardtest.db.* { *; }
-keep class com.test.dbflow.dbflowproguardtest.model.* { *; }