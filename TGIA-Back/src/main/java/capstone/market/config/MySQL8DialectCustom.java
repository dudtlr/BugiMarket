package capstone.market.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MySQL8DialectCustom extends MySQL8Dialect {
    public MySQL8DialectCustom() {
        super();

        // 불린 모드로 MATCH ... AGAINST 함수 등록
        registerFunction(
                "match",
                new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "match (?1) against (?2 IN BOOLEAN MODE)")
        );
    }
}