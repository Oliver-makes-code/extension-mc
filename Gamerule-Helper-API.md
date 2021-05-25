# Gamerule Helper API

## Adding a gamerule

Declair the variable as either a BoolRuleHelper or an IntRuleHelper, then assign inside onInitialize.

`
import com.github.olivermakescode.extension.*

public class ExampleClass implements ModInitializer {
    public static BoolRuleHelper boolRule;
    public static IntRuleHelper intRule;
    
    @Override
    public void onInitialize() {
        boolRule = (BoolRuleHelper) GameruleHelper.register("exampleBool", false);
        intRule = (IntRuleHelper) GameruleHelper.register("exampleInt", 0);
    }
}
`

## Accessing a gamerule
To access a gamerule do BoolRuleHelper#getValue or IntRuleHelper#getValue

`
public class AcessingClass {
    public static void checkRule() {
        if (ExampleClass.boolRule.getValue()) {
            //do something
        }
    }
}
`