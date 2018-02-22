/**
 * The MIT License (MIT)
 * <p>
 * MSUSEL Quamoco Implementation
 * Copyright (c) 2015-2017 Montana State University, Gianforte School of Computing,
 * Software Engineering Laboratory
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package edu.montana.gsoc.msusel.quamoco.model.eval.measure;

import edu.montana.gsoc.msusel.quamoco.io.EvaluationType;
import edu.montana.gsoc.msusel.quamoco.model.Annotation;
import edu.montana.gsoc.msusel.quamoco.model.Source;
import edu.montana.gsoc.msusel.quamoco.model.Tag;
import edu.montana.gsoc.msusel.quamoco.model.eval.MultiMeasureEvaluation;
import edu.montana.gsoc.msusel.quamoco.model.factor.Factor;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

/**
 * Indicates that the factor should aggregate incoming measures using
 * histogram comparison
 *
 * @author Isaac Griffith
 * @version 1.1.1
 */
public class HistCompMultiMeasureEvaluation extends MultiMeasureEvaluation {

    /**
     *
     */
    public HistCompMultiMeasureEvaluation() {
        super();
    }

    /**
     * @param identifier
     */
    public HistCompMultiMeasureEvaluation(String identifier) {
        super(identifier);
    }

    @Builder(buildMethodName = "create")
    protected HistCompMultiMeasureEvaluation(Double completeness, Double maximumPoints, String title, String description, Factor evaluates,
                                             String identifier, Source originatesFrom, @Singular List<Tag> tags, @Singular List<Annotation> annotations) {
        super(completeness, maximumPoints, title, description, evaluates, identifier, originatesFrom, tags, annotations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double evaluate() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String xmlTag() {
        return generateXMLTag(EvaluationType.HIST_COMP_MULTI_MEASURE_EVALUATION.type());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toScript() {
        // TODO Auto-generated method stub
        return null;
    }
}