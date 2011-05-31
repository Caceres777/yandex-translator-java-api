/*
 * Copyright 2011 Jonathan Griggs <jonathan.griggs at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.memetix.mst.translate;

import com.memetix.mst.language.Language;
import com.memetix.mst.MicrosoftAPI;
import java.net.URL;
import java.net.URLEncoder;
/**
 * Translate
 * 
 * Makes calls to the Microsoft Translator API /Translate service
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public final class Translate extends MicrosoftAPI {
    
    private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/Translate";
    
    /**
     * Translates text from a given Language to another given Language using Microsoft Translator.
     * 
     * @param text The String to translate.
     * @param from The language code to translate from.
     * @param to The language code to translate to.
     * @return The translated String.
     * @throws Exception on error.
     */
    public static String execute(final String text, final Language from, final Language to) throws Exception {
        //Run the basic service validations first
        validateServiceState(); 
        final String params = 
                "?from=" + URLEncoder.encode(from.toString(),ENCODING) 
                + "&to=" + URLEncoder.encode(to.toString(),ENCODING) 
                + "&appId=" + URLEncoder.encode(apiKey,ENCODING) 
                + "&text=" + URLEncoder.encode(text,ENCODING);
        
        final URL url = new URL(SERVICE_URL + params);
    	final String response = retrieveString(url);
    	return response;
    }
    
    /**
     * Translates text from a given Language to another given Language using Microsoft Translator.
     * 
     * Default the from to AUTO_DETECT
     * 
     * @param text The String to translate.
     * @param to The language code to translate to.
     * @return The translated String.
     * @throws Exception on error.
     */
    public static String execute(final String text, final Language to) throws Exception {
        return execute(text,Language.AUTO_DETECT,to);
    }
    
}
