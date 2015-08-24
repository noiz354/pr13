package com.noiztezk.pr13.utils;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.noiztezk.pr13.model.Dzkr;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by m.normansyah on 7/23/15.
 * TODO Create documentation and unit testing upon this class.
 */
public class JsonHelper {
    public static final String default_string = "kosong";
    public static final int default_integer = -999
            , bebas = -1;
    public static String version = default_string, version_created = default_string, created_by = default_string;

    public static List readPR13(InputStream inputStream) throws Exception {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
//        Log.d("MNORMANSYAH", findNextTokenType(reader, JsonToken.NAME)+" apakah nama ? ");
        JsonToken token = reader.peek();
        reader.beginObject();
        List returnList = null;
        while(reader.hasNext()){
            String name = reader.nextName();
            if(name.equals("version")){
                version = reader.nextString();
            }else if(name.equals("version_created")){
                version_created = reader.nextString();
            }else if(name.equals("created_by")){
                created_by = reader.nextString();
            }else if(name.equals("dzkir")){
                returnList = readDzkirs(reader);
            }
        }
        reader.endObject();

//        try{
//            return readDzkirs(reader);
//        }finally{
//            reader.close();
//        }
        return returnList;
    }

    /**
	 * Consumes data from the given {@link JsonReader} until the next occurrence
	 * of the given token type is found.
	 *
	 * @param reader
	 *            the data to consume from
	 * @param type
	 *            the type of token to find
	 * @return {@code true} if the next occurrence was found, otherwise
	 *         {@code false}
	 * @throws IOException
	 */
	private static boolean findNextTokenType(JsonReader reader, JsonToken type)
			throws IOException {

		JsonToken token = reader.peek();
		while (token != JsonToken.END_DOCUMENT) {
			if (token == type) {
				return true;
			}

			consume(reader, token);
			token = reader.peek();
		}

		return false;
	}

    /**
	 * Consumes tokens from the reader.
	 *
	 * @param reader
	 *            the instance of the reader
	 * @param type
	 *            the type of token to expect
	 * @throws IOException
	 */
	private static void consume(JsonReader reader, JsonToken type) throws IOException {
        switch (type) {
            case BEGIN_ARRAY:
                reader.beginArray();
                break;
            case BEGIN_OBJECT:
                reader.beginObject();
                break;
            case END_ARRAY:
                reader.endArray();
                break;
            case END_OBJECT:
                reader.endObject();
                break;
            default:
                reader.skipValue();
        }
    }

    public static List readDzkirs(JsonReader reader ) throws Exception{
        List dzkrs = new ArrayList();
        reader.beginArray();
        while(reader.hasNext()){
            dzkrs.add(readDzkir(reader));
        }
        reader.endArray();
        return dzkrs;
    }

    public static Dzkr readDzkir(JsonReader reader) throws IOException{
        String text = default_string;
        String audio = default_string;
        int count = default_integer;
        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            if(name.equals("text")){
                text = reader.nextString();
            }else if(name.equals("audio")){
                audio = reader.nextString();
            }else if(name.equals("count")){
                count = reader.nextInt();
            }
        }
        reader.endObject();
        return new Dzkr(text, audio, count );
    }

    /**
     * each time user tap count then use this class
     * to record location, what time
     */
//    static class DzkrCount{
//        int count;
//        Date curDate;
//        double latitude, langitude;
//    }

//    static class Dzkr{
//        String text, audio;
//        int count;
//
//        public Dzkr(String text, String audio, int count) {
//            this.text = text;
//            this.audio = audio;
//            this.count = count;
//        }
//
//        @Override
//        public String toString() {
//            return "Dzkr{" +
//                    "text='" + text + '\'' +
//                    ", audio='" + audio + '\'' +
//                    ", count=" + count +
//                    '}';
//        }
//    }
}
