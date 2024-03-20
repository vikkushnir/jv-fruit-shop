package core.basesyntax.service.impl;

import core.basesyntax.model.FruitOperation;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitTransactionParser;
import java.util.List;
import java.util.stream.Collectors;

public class FruitTransactionParserImpl implements FruitTransactionParser {
    private static final String COMMA_SPLITTER = ",";
    private static final int COMMAND_INDEX = 0;
    private static final int FRUIT_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;

    @Override
    public List<FruitTransaction> parse(List<String> line) {
        return line.stream()
                .map(this::buildFruitTransaction)
                .collect(Collectors.toList());
    }

    private FruitTransaction buildFruitTransaction(String line) {
        String[] splitLine = line.split(COMMA_SPLITTER);
        FruitOperation operation = FruitOperation
                .getByOperationCode(splitLine[COMMAND_INDEX]);
        String fruit = splitLine[FRUIT_INDEX];
        int quantity = Integer.parseInt(splitLine[QUANTITY_INDEX]);
        return new FruitTransaction(operation, fruit, quantity);
    }
}
