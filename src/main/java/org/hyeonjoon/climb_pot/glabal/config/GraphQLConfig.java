package org.hyeonjoon.climb_pot.config;

import java.time.Instant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLConfig {
    
    @Bean
    public GraphQLScalarType instantScalar() {
        return GraphQLScalarType.newScalar()
            .name("Instant")
            .description("Java Instant scalar")
            .coercing(new graphql.schema.Coercing<Instant, String>() {
                @Override
                public String serialize(Object input) {
                    if (input instanceof Instant instant) {
                        return instant.toString();
                    }
                    throw new CoercingSerializeException("Instant 타입이 아닙니다.");
                }

                @Override
                public Instant parseValue(Object input) {
                    try {
                        return Instant.parse(input.toString());
                    } catch (Exception e) {
                        throw new CoercingParseValueException("잘못된 시간 형식입니다: " + input);
                    }
                }

                @Override
                public Instant parseLiteral(Object input) {
                    try {
                        return Instant.parse(input.toString());
                    } catch (Exception e) {
                        throw new CoercingParseLiteralException("잘못된 시간 형식입니다: " + input);
                    }
                }
            })
            .build();
    }
} 