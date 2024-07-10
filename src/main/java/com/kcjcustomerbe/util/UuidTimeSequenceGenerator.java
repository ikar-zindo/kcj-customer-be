package com.kcjcustomerbe.util;

import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

@RequiredArgsConstructor
public class UuidTimeSequenceGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long currTimeMillis = System.currentTimeMillis();
        return concatUUIDAndTime(currTimeMillis, UUID.randomUUID());
    }

    private UUID concatUUIDAndTime(long currTimeMillis, UUID uuid) {
        String millisHex = Long.toHexString(currTimeMillis);

        String uuidStr = uuid.toString().replace("-", "").substring(0, 16);

        String concatenated = String.format("%016x%s", Long.parseLong(millisHex, 16), uuidStr);
        String concatenatedWithDashes = concatenated.substring(0, 8) + "-" +
                concatenated.substring(8, 12) + "-" +
                concatenated.substring(12, 16) + "-" +
                concatenated.substring(16, 20) + "-" +
                concatenated.substring(20);

        return UUID.fromString(concatenatedWithDashes);
    }
}