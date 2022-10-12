package setup;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ModalDialogDataProvider {
    private static Stream<Arguments> modalUsersData(){
        return Stream.of(Arguments.of("Janusz Tracz","jtracz@gmail.com","password"));
    }
}
