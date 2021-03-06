/**
 * The MIT License (MIT)
 *
 * MSUSEL Quamoco Implementation
 * Copyright (c) 2015-2017 Montana State University, Gianforte School of Computing,
 * Software Engineering Laboratory
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package edu.montana.gsoc.msusel.quamoco.distiller;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.montana.gsoc.msusel.quamoco.distiller.Grade;
import edu.montana.gsoc.msusel.quamoco.distiller.GradeThresholdException;

/**
 * The class <code>GradeTest</code> contains tests for the class
 * <code>{@link Grade}</code>.
 *
 * @generatedBy CodePro at 5/30/15 3:41 PM
 * @author isaac
 * @version $Revision: 1.0 $
 */
public class GradeTest {

    /**
     * Run the int evaluate(Double) method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @Test
    public void testEvaluate_1() throws Exception
    {
        final Grade fixture = Grade.A;
        final BigDecimal val = BigDecimal.ONE;

        final int result = fixture.evaluate(val);

        // TODO: add additional test code here
        Assert.assertEquals(0, result);
    }

    /**
     * Run the int evaluate(Double) method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @Test
    public void testEvaluate_2() throws Exception
    {
        final Grade fixture = Grade.A;
        final BigDecimal val = BigDecimal.ONE;

        final int result = fixture.evaluate(val);

        // TODO: add additional test code here
        Assert.assertEquals(0, result);
    }

    /**
     * Run the int evaluate(Double) method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @Test
    public void testEvaluate_3() throws Exception
    {
        final Grade fixture = Grade.A;
        final BigDecimal val = BigDecimal.ONE;

        final int result = fixture.evaluate(val);

        // TODO: add additional test code here
        Assert.assertEquals(0, result);
    }

    /**
     * Run the List<Grade> getGrades() method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @Test
    public void testGetGrades_1() throws Exception
    {

        final List<Grade> result = Grade.getGrades();

        // TODO: add additional test code here
        Assert.assertNotNull(result);
        Assert.assertEquals(7, result.size());
    }

    /**
     * Run the String getName() method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @Test
    public void testGetName_1() throws Exception
    {
        final Grade fixture = Grade.A;

        final String result = fixture.getName();

        // TODO: add additional test code here
        Assert.assertEquals("A", result);
    }

    /**
     * Run the void setThresholds(double,double) method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @Test
    public void testSetThresholds_1() throws Exception
    {
        final Grade fixture = Grade.A;
        final BigDecimal lower = BigDecimal.ONE;
        final BigDecimal upper = BigDecimal.ONE;

        try
        {
            fixture.setThresholds(lower, upper);
            Assert.assertEquals(lower, fixture.getLowerThreshold());
            Assert.assertEquals(upper, fixture.getUpperThreshold());
        }
        catch (final IllegalArgumentException e)
        {
            Assert.fail();
        }
    }

    /**
     * Run the void setThresholds(double,double) method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @Test(expected = GradeThresholdException.class)
    public void testSetThresholds_2() throws Exception
    {
        final Grade fixture = Grade.A;
        fixture.setThresholds(BigDecimal.ZERO, BigDecimal.ONE);
        final BigDecimal lower = BigDecimal.ONE;
        final BigDecimal upper = BigDecimal.ZERO;

        fixture.setThresholds(lower, upper);
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception
     *             if the initialization fails for some reason
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @Before
    public void setUp() throws Exception
    {
        // TODO: add additional set up code here
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception
     *             if the clean-up fails for some reason
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    @After
    public void tearDown() throws Exception
    {
        // TODO: add additional tear down code here
    }

    /**
     * Launch the test.
     *
     * @param args
     *            the command line arguments
     * @generatedBy CodePro at 5/30/15 3:41 PM
     */
    public static void main(final String[] args)
    {
        new org.junit.runner.JUnitCore().run(GradeTest.class);
    }
}
