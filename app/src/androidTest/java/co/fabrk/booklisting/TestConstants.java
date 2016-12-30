package co.fabrk.booklisting;

import android.annotation.SuppressLint;

/**
 * Created by ebal on 27/12/16.
 */

@SuppressLint("SpellCheckingInspection")
public class TestConstants {

    public final static String JSON_EMPTY_RESPONSE = "{}";

    public final static String JSON_UNEXPECTED_RESPONSE =
            "{"
                    +"        \"unexpected\": {"
                    +"        \"errors\": ["
                    +"        {"
                    +"            \"domain\": \"global\","
                    +"                \"reason\": \"invalidParameter\","
                    +"                \"message\": \"Invalid field selection authors\","
                    +"                \"locationType\": \"parameter\","
                    +"                \"location\": \"fields\""
                    +"        }"
                    +"        ],"
                    +"        \"code\": 400,"
                    +"                \"message\": \"Invalid field selection authors\""
                    +"}"
                    +"}";

    public final static String JSON_ERROR_RESPONSE =
        "{"
        +"        \"error\": {"
        +"        \"errors\": ["
        +"        {"
        +"            \"domain\": \"global\","
        +"                \"reason\": \"invalidParameter\","
        +"                \"message\": \"Invalid field selection authors\","
        +"                \"locationType\": \"parameter\","
        +"                \"location\": \"fields\""
        +"        }"
        +"        ],"
        +"        \"code\": 400,"
        +"                \"message\": \"Invalid field selection authors\""
        +"}"
        +"}";

    public final static String JSON_NO_VOLUME_INFO_RESPONSE =
            "{"
                    + "        \"items\": ["
                    + "       ]"
                    + "    }";
    
    public final static String JSON_VALID_RESPONSE =
            "{"
                    + "        \"items\": ["
                    + "        {"
                    + "           \"volumeInfo\": {"
                    + "           \"title\": \"Pro Android C++ with the NDK\","
                    + "           \"authors\": ["
                    + "                     \"Onur Cinar\""
                    + "           ]"
                    + "       }"
                    + "       },"
                    + "       {"
                    + "           \"volumeInfo\": {"
                    + "           \"title\": \"Histoire de Jules César guerre civile: Du passage du Rubicon à la bataille\","
                    + "           \"authors\": ["
                    + "                     \"Eugène\","
                    + "                     \"Georges\","
                    + "                     \"Henri\","
                    + "                     \"Céleste baron Stoffel\""
                    + "           ]"
                    + "       }"
                    + "       }"
                    + "       ]"
                    + "    }";

    public final static String JSON_NO_TITLE_RESPONSE =
            "{"
                    + "        \"items\": ["
                    + "        {"
                    + "           \"volumeInfo\": {"
                    + "           \"authors\": ["
                    + "                 \"Onur Cinar\""
                    + "           ]"
                    + "       }"
                    + "       },"
                    + "       {"
                    + "           \"volumeInfo\": {"
                    + "           \"title\": \"Histoire de Jules César guerre civile: Du passage du Rubicon à la bataille\","
                    + "           \"authors\": ["
                    + "                 \"Eugène-Georges-Henri-Céleste baron Stoffel\","
                    + "                 \"Eugène-Georges-Henri-Céleste baron Stoffel\","
                    + "                 \"Eugène-Georges-Henri-Céleste baron Stoffel\","
                    + "                 \"Eugène-Georges-Henri-Céleste baron Stoffel\""
                    + "           ]"
                    + "       }"
                    + "       }"
                    + "       ]"
                    + "    }";


    public final static String JSON_NO_AUTHOR_RESPONSE =
            "{"
                    + "        \"items\": ["
                    + "        {"
                    + "           \"volumeInfo\": {"
                    + "           \"title\": \"Pro Android C++ with the NDK\""
                    + "       }"
                    + "       },"
                    + "       {"
                    + "           \"volumeInfo\": {"
                    + "           \"title\": \"Histoire de Jules César guerre civile: Du passage du Rubicon à la bataille\","
                    + "                   \"authors\": ["
                    + "           \"Eugène-Georges-Henri-Céleste baron Stoffel\","
                    + "           \"Eugène-Georges-Henri-Céleste baron Stoffel\","
                    + "           \"Eugène-Georges-Henri-Céleste baron Stoffel\","
                    + "           \"Eugène-Georges-Henri-Céleste baron Stoffel\""
                    + "           ]"
                    + "       }"
                    + "       }"
                    + "       ]"
                    + "    }";

    public final static String JSON_MALFORMED_RESPONSE =
            "{"
                    + "        \"items\": ["
                    + "        {"
                    + "           \"volumeInfo\": {"
                    + "           \"title\": \"Pro Android C++ with the NDK\","
                    + "           \"authors\": ["
                    + "                     \"Onur Cinar\""
                    + "           ]"
                    + "       }"
                    + "       },"
                    + "       {"
                    + "           \"volumeInfo\": {"
                    + "           \"title\": \"Histoire de Jules César guerre civile: Du passage du Rubicon à la bataille\","
                    + "           \"authors\": ["
                    + "                     \"Eugène\","
                    + "                     \"Georges\","
                    + "                     \"Henri\","
                    + "                     \"Céleste baron Stoffel\""
                    + "           ]"
                    + "    }";
}
