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
package edu.montana.gsoc.msusel.quamoco.model.factor;

import edu.montana.gsoc.msusel.quamoco.io.FactorType;
import edu.montana.gsoc.msusel.quamoco.model.*;
import edu.montana.gsoc.msusel.quamoco.model.entity.Entity;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

/**
 * On a high level of abstraction, product quality contributes to the
 * satisfaction of specific stakeholders. This can also be modeled using the
 * stakeholder as an entity.
 * 
 * @author Isaac Griffith
 * @version 1.1.1
 */
public class StakeholderSatisfaction extends Factor {

    /**
     * Constructs a new StakeholderSatisfaction with the given name
     * 
     * @param name
     *            The name
     */
    public StakeholderSatisfaction(String name)
    {
        super(name);
    }

    /**
     * @param name
     * @param identifier
     */
    public StakeholderSatisfaction(String name, String identifier)
    {
        super(name, identifier);
    }

    @Builder(buildMethodName = "create")
    protected StakeholderSatisfaction(String name, String description, Entity characterizes, String title, @Singular List<Impact> influences, Factor refines,
                                      String identifier, Source originatesFrom, @Singular List<Tag> tags, @Singular List<Annotation> annotations)
    {
        super(name, description, characterizes, title,influences, refines,
                identifier, originatesFrom, tags, annotations);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String xmlTag()
    {
        return generateXMLTag(FactorType.STAKEHOLDER_SATISFACTION.type());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toScript()
    {
        // TODO Auto-generated method stub
        return null;
    }
}