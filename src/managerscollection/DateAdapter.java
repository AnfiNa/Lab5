package managerscollection;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
    @Override
    public JsonElement serialize(ZonedDateTime date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)); // "yyyy-mm-dd"
    }

    @Override
    public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString());
    }
}
