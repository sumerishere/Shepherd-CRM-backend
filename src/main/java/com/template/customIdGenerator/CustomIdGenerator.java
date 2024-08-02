package com.template.customIdGenerator;

import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Random;

public class CustomIdGenerator implements BeforeExecutionGenerator {
	
	private static final long serialVersionUID = 1L; // Adding serialVersionUID
	
    private static final int MIN = 1000; // Starting value for 4-digit IDs
    private static final int MAX = 9999; // Ending value for 4-digit IDs

//    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object, EventType eventType) {
        Random random = new Random();
        return (long) (random.nextInt((MAX - MIN) + 1) + MIN);
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT); // Handle only insert events
    }

    // Ensure this method also returns the generated ID if used
    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue,
                           EventType eventType) {
        return generate(session, owner, eventType);
    }
}

