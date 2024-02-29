package rpg.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Super Type Token 으로 사용할 Abstract Class
 * 해당 클래스를 상속받는 익명 Class를 Key로 사용해 타입 안전 이종 컨테이너를 사용할 수 있다.
 * 다만 Super Type Token은 조심해서 사용해야 한다.
 * @param <T> Type Token으로 사용할 Generic Type
 *           Generic Type이 아닌 경우, Class<T> Class의 사용을 고려할 것
 */
public abstract class TypeReference<T>{
    private final Type type;

    public TypeReference() {
        this.type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    public Type getType() {
        return this.type;
    }

}
