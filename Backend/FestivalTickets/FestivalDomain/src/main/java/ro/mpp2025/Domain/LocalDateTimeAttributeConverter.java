package ro.mpp2025.Domain;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        System.out.println(">> CONVERTER: Writing " + locDateTime);
        return locDateTime != null ? Timestamp.valueOf(locDateTime) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        System.out.println(">> CONVERTER: Reading " + sqlTimestamp);

        return sqlTimestamp != null ? sqlTimestamp.toLocalDateTime() : null;
    }
}