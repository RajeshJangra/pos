/*
 *  Proprietary and confidential
 *  Copyright (C) Mckinsey & Company, Inc - All Rights Reserved (2016)
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 */

package com.altisource.pos.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static com.altisource.pos.util.Constant.DATE_FORMAT;

/**
 * Created by RAJESHKUMAR on 3/26/16.
 */
public class PosDateDeserializer extends JsonDeserializer<Date> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PosDateDeserializer.class);

    @Override
    public Date deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        try {
            return DATE_FORMAT.parse(jp.getValueAsString());
        } catch (ParseException e) {
            final String msg = "Error while deserializing date: " + jp.getValueAsString();
            LOGGER.error(msg);
            throw new IOException(msg, e);
        }
    }
}
