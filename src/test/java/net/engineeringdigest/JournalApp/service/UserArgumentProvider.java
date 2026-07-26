package net.engineeringdigest.JournalApp.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
            ExtensionContext context) {

        return Stream.of(
                Arguments.of("1", "1", "2"),
                Arguments.of("2", "10", "12"),
                Arguments.of("3", "3", "9")
        );
    }
}