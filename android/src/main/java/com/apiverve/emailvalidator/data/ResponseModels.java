// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     EmailValidatorData data = Converter.fromJsonString(jsonString);

package com.apiverve.emailvalidator.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static EmailValidatorData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(EmailValidatorData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(EmailValidatorData.class);
        writer = mapper.writerFor(EmailValidatorData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// EmailValidatorData.java

package com.apiverve.emailvalidator.data;

import com.fasterxml.jackson.annotation.*;

public class EmailValidatorData {
    private Object creationDate;
    private String domain;
    private String email;
    private String username;
    private boolean canConnect;
    private boolean hasTypo;
    private boolean isValid;
    private boolean isMXValid;
    private boolean isSMTPValid;
    private boolean isRegexValid;
    private SMTP smtp;
    private boolean isCompanyEmail;
    private boolean isFreeEmail;
    private long checksum;

    @JsonProperty("creationDate")
    public Object getCreationDate() { return creationDate; }
    @JsonProperty("creationDate")
    public void setCreationDate(Object value) { this.creationDate = value; }

    @JsonProperty("domain")
    public String getDomain() { return domain; }
    @JsonProperty("domain")
    public void setDomain(String value) { this.domain = value; }

    @JsonProperty("email")
    public String getEmail() { return email; }
    @JsonProperty("email")
    public void setEmail(String value) { this.email = value; }

    @JsonProperty("username")
    public String getUsername() { return username; }
    @JsonProperty("username")
    public void setUsername(String value) { this.username = value; }

    @JsonProperty("canConnect")
    public boolean getCanConnect() { return canConnect; }
    @JsonProperty("canConnect")
    public void setCanConnect(boolean value) { this.canConnect = value; }

    @JsonProperty("hasTypo")
    public boolean getHasTypo() { return hasTypo; }
    @JsonProperty("hasTypo")
    public void setHasTypo(boolean value) { this.hasTypo = value; }

    @JsonProperty("isValid")
    public boolean getIsValid() { return isValid; }
    @JsonProperty("isValid")
    public void setIsValid(boolean value) { this.isValid = value; }

    @JsonProperty("isMxValid")
    public boolean getIsMXValid() { return isMXValid; }
    @JsonProperty("isMxValid")
    public void setIsMXValid(boolean value) { this.isMXValid = value; }

    @JsonProperty("isSmtpValid")
    public boolean getIsSMTPValid() { return isSMTPValid; }
    @JsonProperty("isSmtpValid")
    public void setIsSMTPValid(boolean value) { this.isSMTPValid = value; }

    @JsonProperty("isRegexValid")
    public boolean getIsRegexValid() { return isRegexValid; }
    @JsonProperty("isRegexValid")
    public void setIsRegexValid(boolean value) { this.isRegexValid = value; }

    @JsonProperty("smtp")
    public SMTP getSMTP() { return smtp; }
    @JsonProperty("smtp")
    public void setSMTP(SMTP value) { this.smtp = value; }

    @JsonProperty("isCompanyEmail")
    public boolean getIsCompanyEmail() { return isCompanyEmail; }
    @JsonProperty("isCompanyEmail")
    public void setIsCompanyEmail(boolean value) { this.isCompanyEmail = value; }

    @JsonProperty("isFreeEmail")
    public boolean getIsFreeEmail() { return isFreeEmail; }
    @JsonProperty("isFreeEmail")
    public void setIsFreeEmail(boolean value) { this.isFreeEmail = value; }

    @JsonProperty("checksum")
    public long getChecksum() { return checksum; }
    @JsonProperty("checksum")
    public void setChecksum(long value) { this.checksum = value; }
}

// SMTP.java

package com.apiverve.emailvalidator.data;

import com.fasterxml.jackson.annotation.*;

public class SMTP {
    private boolean valid;
    private String reason;

    @JsonProperty("valid")
    public boolean getValid() { return valid; }
    @JsonProperty("valid")
    public void setValid(boolean value) { this.valid = value; }

    @JsonProperty("reason")
    public String getReason() { return reason; }
    @JsonProperty("reason")
    public void setReason(String value) { this.reason = value; }
}