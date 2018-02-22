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
package edu.montana.gsoc.msusel.quamoco.model;

import edu.montana.gsoc.msusel.quamoco.model.measure.Measure;
import edu.montana.gsoc.msusel.quamoco.model.measurement.ManualInstrument;
import edu.montana.gsoc.msusel.quamoco.model.measurement.MeasurementMethod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MeasurementMethodTest {

    MeasurementMethod element;

    @Before
    public void setUp() throws Exception {
        element = ManualInstrument.builder().identifier("instrument")
                .determines(mock(Measure.class))
                .metric("name")
                .create();
    }

    @Test
    public void getDetermines() throws Exception {
        assertNotNull(element.getDetermines());
    }

    @Test
    public void setDetermines() throws Exception {
        element.setDetermines(null);
        assertNull(element.getDetermines());
        element.setDetermines(mock(Measure.class));
        assertNotNull(element.getDetermines());
    }

    @Test
    public void getMetric() throws Exception {
        assertEquals("name", element.getName());
    }

    @Test
    public void setMetric() throws Exception {
        element.setName(null);
        assertNull(element.getName());
        element.setName("newMetric");
        assertEquals("newMetric", element.getName());
    }

}