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

import java.util.List;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import edu.montana.gsoc.msusel.quamoco.graph.INode;
import edu.montana.gsoc.msusel.quamoco.model.AbstractEntity;
import edu.montana.gsoc.msusel.quamoco.model.qm.AbstractQMEntity;
import edu.montana.gsoc.msusel.quamoco.model.qm.Evaluation;
import edu.montana.gsoc.msusel.quamoco.model.qm.Factor;
import edu.montana.gsoc.msusel.quamoco.model.qm.Measure;
import edu.montana.gsoc.msusel.quamoco.model.qm.MeasurementMethod;
import edu.montana.gsoc.msusel.quamoco.model.qm.QualityModel;
import edu.montana.gsoc.msusel.quamoco.model.qm.Requires;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

/**
 * A utility class for dealing with quality model objects.
 *
 * @author Isaac Griffith
 * @version 1.1.1
 */
public final class QualityModelUtils {

    /**
     * Private constructor
     */
    private QualityModelUtils()
    {
    }

    /**
     * Creates a map relating model names to quality models
     *
     * @param models
     *            a list of existing quality models
     * @return The map that was created
     */
    public static Map<String, QualityModel> createModelMap(final List<QualityModel> models)
    {
        final Map<String, QualityModel> map = Maps.newHashMap();
        for (final QualityModel model : models)
        {
            if (model == null)
            {
                continue;
            }
            map.put(model.getName(), model);
        }

        return map;
    }

    /**
     * Finds an entity by its identifier within the provided model map, if such
     * an entity exists.
     *
     * @param modelMap
     *            A map of models indexed by their names.
     * @param id
     *            The unique identifying string associated with the entity.
     * @return The entity with the provided id or null if no such entity exists.
     */
    public static AbstractEntity findEntity(final Map<String, QualityModel> modelMap, final String id)
    {
        if (modelMap == null || modelMap.isEmpty() || id == null || id.isEmpty())
        {
            return null;
        }

        AbstractEntity entity = null;

        if (id.contains(".qm#"))
        {
            final String[] keys = id.split(".qm#");
            final String model = keys[0];
            entity = modelMap.get(model).find(id);
        }
        else
        {
            for (final QualityModel model : modelMap.values())
            {
                final String temp = model.getName() + ".qm#" + id;
                if (model.hasKey(temp))
                {
                    entity = model.find(temp);
                    break;
                }
            }
        }

        return entity;
    }

    /**
     * Retrieves all MeasurementMethod entities from a list of known quality
     * models.
     *
     * @param models
     *            The list of quality models
     * @return List of all known measurement method objects.
     */
    public static List<MeasurementMethod> getAllMeasurementMethods(final List<QualityModel> models)
    {
        final List<MeasurementMethod> mmlist = Lists.newArrayList();

        if (models != null)
        {
            for (final QualityModel model : models)
            {
                for (final AbstractQMEntity entity : model.getContained())
                {
                    if (entity instanceof MeasurementMethod)
                    {
                        mmlist.add((MeasurementMethod) entity);
                    }
                }
            }
        }

        return mmlist;
    }

    /**
     * Retrieves an Evaluation entity from a map of quality models indexed by
     * name when given a node representing an entity evaluated by the returned
     * evaluation.
     *
     * @param dest
     *            Node that whose quality model representation is evaluated by
     *            the desired evaluation.
     * @param modelMap
     *            Map of quality models indexed by their names.
     * @return The evaluation object that evaluates the provided node, and
     *         exists within one of the quality models found in the map. If no
     *         such evaluation exists, null is returned.
     */
    public static Evaluation getEvaluates(final INode dest, final Map<String, QualityModel> modelMap)
    {
        final AbstractEntity ent = QualityModelUtils.findEntity(modelMap, dest.getOwnedBy());
        if (ent != null && ent instanceof Evaluation)
        {
            return (Evaluation) ent;
        }
        return null;
    }

    /**
     * Retrieves a factor object from a quality model provided in the model map,
     * that is represented by the provided node.
     *
     * @param source
     *            Node which represents the factor.
     * @param modelMap
     *            Map of quality models indexed by name.
     * @return The factor if it exists within one of the quality models,
     *         otherwise null.
     */
    public static Factor getFactor(final INode source, final Map<String, QualityModel> modelMap)
    {
        final AbstractEntity ent = QualityModelUtils.findEntity(modelMap, source.getOwnedBy());
        if (ent != null && ent instanceof Factor)
        {
            return (Factor) ent;
        }
        return null;
    }

    /**
     * Retrieves a measure object from a quality model provided in the model
     * map, that is represented by the provided node.
     *
     * @param source
     *            Node which represents the measure.
     * @param modelMap
     *            Map of quality models indexed by name.
     * @return The measure if it exists within one of the quality models,
     *         otherwise null.
     */
    public static Measure getMeasure(String id, final Map<String, QualityModel> modelMap)
    {
        final AbstractEntity ent = QualityModelUtils.findEntity(modelMap, id);
        if (ent != null && ent instanceof Measure)
        {
            return (Measure) ent;
        }
        return null;
    }

    /**
     * Retrieves the Factor with the given identifier from the known quality
     * models in the given model map
     * 
     * @param id
     *            Identifier of the Factor to search for
     * @param modelMap
     *            Map of all known quality models indexed by identifier
     * @return Factor with given identifier, or null if no such Factor exists.
     */
    public static Factor getFactor(String id, final Map<String, QualityModel> modelMap)
    {
        final AbstractEntity ent = QualityModelUtils.findEntity(modelMap, id);
        if (ent != null && ent instanceof Factor)
        {
            return (Factor) ent;
        }
        return null;
    }

    /**
     * Retrieves all Evaluation entities from a list of known quality
     * models.
     *
     * @param models
     *            The list of quality models
     * @return List of all known evaluation objects.
     */
    public static List<Evaluation> getAllEvaluations(final List<QualityModel> models)
    {
        final List<Evaluation> evalList = Lists.newArrayList();
        List<QualityModel> sorted = getSortedModelList(models);

        if (sorted != null)
        {
            for (final QualityModel model : sorted)
            {
                for (final AbstractQMEntity entity : model.getContained())
                {
                    if (entity instanceof Evaluation)
                    {
                        evalList.add((Evaluation) entity);
                    }
                }
            }
        }

        return evalList;
    }

    /**
     * Produces a sorted list of known quality models, which are sorted by least
     * dependent first.
     * 
     * @param models
     *            Unsorted List of known quality models
     * @return Sorted List of known quality models in ascending order of
     *         dependence
     */
    @VisibleForTesting
    static List<QualityModel> getSortedModelList(List<QualityModel> models)
    {
        DirectedSparseGraph<QualityModel, String> g = new DirectedSparseGraph<>();
        if (models != null)
        {
            for (QualityModel model : models)
            {
                g.addVertex(model);
            }
            for (QualityModel model : models)
            {
                for (Requires r : model.getRequires())
                {
                    String s = r.getHREF();
                    if (s.contains("#"))
                    {
                        for (QualityModel m : models)
                        {
                            if (m.getId().equals(s.split(".qm#")[1]))
                            {
                                g.addEdge("requires: " + model.getName() + " - " + m.getName(), model, m);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return topoSort(g);
    }

    /**
     * Conducts a topological sort of a dependency graph between quality models.
     * 
     * @param graph
     *            Dependency graph of the quality models. Note that edges flow
     *            in opposite direction of dependency.
     * @return Topologically sorted list of quality models
     */
    @VisibleForTesting
    static List<QualityModel> topoSort(DirectedSparseGraph<QualityModel, String> graph)
    {
        List<QualityModel> list = Lists.newArrayList();
        List<QualityModel> set = Lists.newArrayList();

        for (QualityModel q : graph.getVertices())
        {
            if (graph.inDegree(q) <= 0)
            {
                set.add(q);
            }
        }

        while (!set.isEmpty())
        {
            QualityModel q = set.remove(0);
            list.add(q);
            List<String> toRemove = Lists.newArrayList();
            for (String edge : graph.getOutEdges(q))
            {
                QualityModel x = graph.getOpposite(q, edge);
                toRemove.add(edge);
                if (graph.inDegree(x) <= 1)
                {
                    set.add(x);
                }
            }
            for (String edge : toRemove)
            {
                graph.removeEdge(edge);
            }
        }
        if (graph.getEdgeCount() > 0)
        {
            System.out.println("Graph has Cycles");
        }
        return list;
    }
}
