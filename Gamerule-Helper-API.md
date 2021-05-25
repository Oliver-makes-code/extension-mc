# Gamerule Helper API

## Adding a gamerule

Declare the variable as either a BoolRuleHelper or an IntRuleHelper, then assign inside onInitialize.

```java
package path.to;

import com.github.olivermakescode.extension.*;
import net.fabricmc.api.ModInitializer;

public class ExampleClass implements ModInitializer {
    //initialize variables
    public static BoolRuleHelper boolRule;
    public static IntRuleHelper intRule;
    
    @Override
    public void onInitialize() {
        //set variables
        boolRule = (BoolRuleHelper) GameruleHelper.register("exampleBool", false);
        intRule = (IntRuleHelper) GameruleHelper.register("exampleInt", 0);
    }
}
```

## Accessing a gamerule
To access a gamerule do BoolRuleHelper#getValue or IntRuleHelper#getValue

```java
package path.to;

public class AccessingClass {
    public static void checkRule() {
        if (ExampleClass.boolRule.getValue()) {
            //do something
            System.out.println(ExampleClass.intRule.getValue());
        }
    }
}
```