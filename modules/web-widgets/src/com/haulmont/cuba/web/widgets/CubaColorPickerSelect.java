/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.cuba.web.widgets;

import com.vaadin.ui.Component;
import com.vaadin.ui.components.colorpicker.ColorPickerSelect;

public class CubaColorPickerSelect extends ColorPickerSelect {

    @Override
    protected Component initContent() {
        Component component = super.initContent();

        // VAADIN8: gg, there is no method
//        range.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
        range.setTextInputAllowed(false);

        return component;
    }

    public void setAllCaption(String allCaption) {
        // VAADIN8: gg, use itemCaptionGenerator
//        range.setItemCaption(ColorRange.ALL, allCaption);
    }

    public void setRedCaption(String redCaption) {
        // VAADIN8: gg, use itemCaptionGenerator
//        range.setItemCaption(ColorRange.RED, redCaption);
    }

    public void setGreenCaption(String greenCaption) {
        // VAADIN8: gg, use itemCaptionGenerator
//        range.setItemCaption(ColorRange.GREEN, greenCaption);
    }

    public void setBlueCaption(String blueCaption) {
        // VAADIN8: gg, use itemCaptionGenerator
//        range.setItemCaption(ColorRange.BLUE, blueCaption);
    }
}