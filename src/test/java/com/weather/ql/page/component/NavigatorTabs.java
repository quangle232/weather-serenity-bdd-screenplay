package com.weather.ql.page.component;

import net.serenitybdd.screenplay.targets.Target;

public class NavigatorTabs {
    public static Target NAVIGATOR_TAB = Target.the("Ten days tab")
                                        .locatedBy("//*[text() = '{0}']");

}
