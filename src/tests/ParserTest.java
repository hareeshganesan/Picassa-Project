package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.awt.Color;
import model.ParserException;
import model.RGBColor;
import model.Parser;
import org.junit.Before;
import org.junit.Test;


public class ParserTest
{
    // useful constants
    private static final RGBColor BLACK = new RGBColor(Color.BLACK);
    private static final RGBColor GRAY = new RGBColor(0.5);
    private static final RGBColor WHITE = new RGBColor(Color.WHITE);


    @Before
    public void setUp ()
    {
        // initialize stuff here
    }


    @Test
    public void testConstant ()
    {
        RGBColor actual =
            (new Parser("1")).makeExpression().evaluateWithVariables(0, 0, 0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("-1")).makeExpression().evaluateWithVariables(0, 0, 0);
        assertTrue(BLACK.equals(actual));
        actual =
            (new Parser("0.5")).makeExpression().evaluateWithVariables(0, 0, 0);
        assertTrue(GRAY.equals(actual));
        actual =
            (new Parser(".5")).makeExpression().evaluateWithVariables(0, 0, 0);
        assertTrue(GRAY.equals(actual));
        actual =
            (new Parser("(1.0)")).makeExpression().evaluateWithVariables(0,
                                                                         0,
                                                                         0);
        assertTrue(WHITE.equals(actual));
        try
        {
            (new Parser("0.5 0.5")).makeExpression().evaluateWithVariables(0,
                                                                           0,
                                                                           0);
            fail("Exception should have been thrown");
        }
        catch (ParserException e)
        {
            // actually that's good
            assertEquals(ParserException.Type.EXTRA_CHARACTERS, e.getType());
        }
    }


    @Test
    public void testBinaryOps ()
    {
        RGBColor actual =
            (new Parser("(plus .1 .9)")).makeExpression()
                                        .evaluateWithVariables(0, 0, 0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(plus (plus 0.01 0.09) (plus 0.4 0.5))")).makeExpression()
                                                                  .evaluateWithVariables(0,
                                                                                         0,
                                                                                         0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("    (plus(plus 0.01 0.09)(plus 0.4 0.5   ))    ")).makeExpression()
                                                                           .evaluateWithVariables(0,
                                                                                                  0,
                                                                                                  0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(minus (div 1.8 2) (mul -10 0.01))")).makeExpression()
                                                              .evaluateWithVariables(0,
                                                                                     0,
                                                                                     0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(mod (exp 3 2) 8)")).makeExpression()
                                             .evaluateWithVariables(0, 0, 0);
        assertTrue(WHITE.equals(actual));

        actual =
            (new Parser("(+ .1 .9)")).makeExpression().evaluateWithVariables(0,
                                                                             0,
                                                                             0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(+ (+ 0.01 0.09) (+ 0.4 0.5))")).makeExpression()
                                                         .evaluateWithVariables(0,
                                                                                0,
                                                                                0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("    (+(+ 0.01 0.09)(+ 0.4 0.5   ))    ")).makeExpression()
                                                                  .evaluateWithVariables(0,
                                                                                         0,
                                                                                         0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(- (/ 1.8 2) (* -10 0.01))")).makeExpression()
                                                      .evaluateWithVariables(0,
                                                                             0,
                                                                             0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(% (^ 3 2) 8)")).makeExpression()
                                         .evaluateWithVariables(0, 0, 0);
        assertTrue(WHITE.equals(actual));

        try
        {
            (new Parser("(fooo 0.1 0.9)")).makeExpression()
                                          .evaluateWithVariables(0, 0, 0);
            fail("Exception should have been thrown");
        }
        catch (ParserException e)
        {
            // actually that's good
            assertEquals(ParserException.Type.UNKNOWN_COMMAND, e.getType());
        }
    }


    @Test
    public void testTransforms ()
    {
        RGBColor actual =
            (new Parser("(plus (color .3 .3 .3) (neg 1.3))")).makeExpression()
                                                             .evaluateWithVariables(0,
                                                                                    0,
                                                                                    0);
        assertTrue(BLACK.equals(actual));
        actual =
            (new Parser("(neg (plus 0 -1))")).makeExpression()
                                             .evaluateWithVariables(0, 0, 0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(floor 1.2)")).makeExpression()
                                       .evaluateWithVariables(0, 0, 0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(ceil .7)")).makeExpression().evaluateWithVariables(0,
                                                                             0,
                                                                             0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(abs -1)")).makeExpression().evaluateWithVariables(0,
                                                                            0,
                                                                            0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(clamp 1.3)")).makeExpression()
                                       .evaluateWithVariables(0, 0, 0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(wrap 3)")).makeExpression().evaluateWithVariables(0,
                                                                            0,
                                                                            0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(minus (sin 0) 1)")).makeExpression()
                                             .evaluateWithVariables(0, 0, 0);
        assertTrue(BLACK.equals(actual));
        actual =
            (new Parser("(cos 0)")).makeExpression().evaluateWithVariables(0,
                                                                           0,
                                                                           0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(minus (tan 0) 1)")).makeExpression()
                                             .evaluateWithVariables(0, 0, 0);
        assertTrue(BLACK.equals(actual));
        actual =
            (new Parser("(minus (min 0 .5 1) 1)")).makeExpression()
                                                  .evaluateWithVariables(0,
                                                                         0,
                                                                         0);
        assertTrue(BLACK.equals(actual));
        actual =
            (new Parser("(minus (max 1) 0)")).makeExpression()
                                             .evaluateWithVariables(0, 0, 0);
        assertTrue(WHITE.equals(actual));

        actual =
            (new Parser("(minus (min 0 .5 1) -1)")).makeExpression()
                                                   .evaluateWithVariables(0,
                                                                          0,
                                                                          0);
        assertTrue(WHITE.equals(actual));

        actual =
            (new Parser("(minus (max 0 .5 1) 0)")).makeExpression()
                                                  .evaluateWithVariables(0,
                                                                         0,
                                                                         0);
        assertTrue(WHITE.equals(actual));

        actual =
            (new Parser("(minus (sum .1 .2 .3 .4) 0)")).makeExpression()
                                                       .evaluateWithVariables(0,
                                                                              0,
                                                                              0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(minus (sum .1 .2 ) -0.7)")).makeExpression()
                                                     .evaluateWithVariables(0,
                                                                            0,
                                                                            0);
        assertTrue(WHITE.equals(actual));
        actual =
            (new Parser("(minus (product 1 3 .1) -0.7)")).makeExpression()
                                                         .evaluateWithVariables(0,
                                                                                0,
                                                                                0);
        assertTrue(WHITE.equals(actual));

        actual =
            (new Parser("(minus (average 0 1 .5) -0.5)")).makeExpression()
                                                         .evaluateWithVariables(0,
                                                                                0,
                                                                                0);
        assertTrue(WHITE.equals(actual));
        
        
        actual =
                (new Parser("(minus (if -1 1 .5) -0.5)")).makeExpression()
                                                             .evaluateWithVariables(0,
                                                                                    0,
                                                                                    0);
        assertTrue(WHITE.equals(actual));
        
        
        actual =
                (new Parser("(minus (if 1 .5 1) -0.5)")).makeExpression()
                                                             .evaluateWithVariables(0,
                                                                                    0,
                                                                                    0);
        assertTrue(WHITE.equals(actual));

        actual =
            (new Parser("(let myVar .7 (minus myVar 1.7))")).makeExpression()
                                                            .evaluateWithVariables(0,
                                                                                   0,
                                                                                   0);
        assertTrue(BLACK.equals(actual));
        actual =
            (new Parser("(let myVar -1.7 (plus myVar (let myVar .7 myVar))) ")).makeExpression()
                                                                               .evaluateWithVariables(0,
                                                                                                      0,
                                                                                                      0);
        assertTrue(BLACK.equals(actual));
    }

}
