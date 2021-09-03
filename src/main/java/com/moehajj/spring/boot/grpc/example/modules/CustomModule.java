package com.moehajj.spring.boot.grpc.example.modules;

import net.starlark.java.annot.Param;
import net.starlark.java.annot.ParamType;
import net.starlark.java.annot.StarlarkBuiltin;
import net.starlark.java.annot.StarlarkMethod;
import net.starlark.java.eval.EvalException;
import net.starlark.java.eval.NoneType;
import net.starlark.java.eval.StarlarkValue;

@StarlarkBuiltin(
    name = "custom",
    category = "BUILTIN",
    doc = "a custom module")
public class CustomModule implements StarlarkValue {

    public static final CustomModule INSTANCE = new CustomModule();

    @StarlarkMethod(
        name = "doSomething",
        doc = "does something custom",
        parameters = {
            @Param(
                name = "param1",
                doc = "my first parameter",
                allowedTypes = {
                    @ParamType(type = String.class),
                }),
            @Param(
                name = "param2",
                doc = "my second (optional) parameter",
                named = true,
                defaultValue = "None",
                allowedTypes = {
                    @ParamType(type = NoneType.class),
                    @ParamType(type = String.class),
                })
        })
    public Object doSomething(Object param1, Object param2) throws EvalException {
        if (param2.toString().equals("None")) {
            return param1;
        }
        return param1.toString() + param2.toString();
    }

}