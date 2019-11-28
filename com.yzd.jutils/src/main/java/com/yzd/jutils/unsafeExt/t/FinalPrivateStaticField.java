package com.yzd.jutils.unsafeExt.t;

/**
 * @author: yaozhendong
 * @create: 2019-11-28 13:19
 **/

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FinalPrivateStaticField {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        {
            //try updating a String field first
            Field field = SafeKlass.class.getDeclaredField( "CONSTANT" );
            field.setAccessible( true );

            //'modifiers' - it is a field of a class called 'Field'. Make it accessible and remove
            //'final' modifier for our 'CONSTANT' field
            Field modifiersField = Field.class.getDeclaredField( "modifiers" );
            modifiersField.setAccessible( true );
            modifiersField.setInt( field, field.getModifiers() & ~Modifier.FINAL );

            //it updates a field, but it was already inlined during compilation...
            field.set( null, "I was updated!" );

            //this method call will return an inlined value
            System.out.println( SafeKlass.getConstant() );
            //the only way to actually see an updated value is again via reflection
            System.out.println( SafeKlass.getConstantReflection() );
        }
        {
            //now try to update not constant expression type field
            Field field = SafeKlass.class.getDeclaredField( "NOT_SO_CONSTANT" );
            field.setAccessible( true );

            //'modifiers' - it is a field of a class called 'Field'. Make it accessible and remove
            //'final' modifier for our 'CONSTANT' field
            Field modifiersField = Field.class.getDeclaredField( "modifiers" );
            modifiersField.setAccessible( true );
            modifiersField.setInt( field, field.getModifiers() & ~Modifier.FINAL );

            //this update should actually work
            field.set( null, -20 );

            System.out.println( SafeKlass.getNotSoConstant() );
        }
    }
}