package edu.montana.gsoc.msusel.quamoco.distiller;

import com.google.common.annotations.VisibleForTesting;
import edu.montana.gsoc.msusel.quamoco.graph.node.*;
import edu.montana.gsoc.msusel.quamoco.model.MeasureType;
import edu.montana.gsoc.msusel.quamoco.model.QMElement;
import edu.montana.gsoc.msusel.quamoco.model.factor.Factor;
import edu.montana.gsoc.msusel.quamoco.model.measure.Measure;
import edu.montana.gsoc.msusel.quamoco.model.measurement.ManualInstrument;
import edu.montana.gsoc.msusel.quamoco.model.measurement.MeasurementMethod;
import edu.montana.gsoc.msusel.quamoco.model.measurement.ToolBasedInstrument;

public class NodeFactory {

    private NodeFactory() {

    }

    private static final class Holder {
        public static final NodeFactory INSTANCE = new NodeFactory();
    }

    public static NodeFactory getInstance() {
        return Holder.INSTANCE;
    }

    public Node createNode(QMElement element) {
        if (element instanceof Factor) {
            return createFactorNode((Factor) element);
        } else if (element instanceof Measure) {
            return createMeasureNode((Measure) element);
        } else if (element instanceof MeasurementMethod) {
            return createProviderNode((MeasurementMethod) element);
        }
        return null;
    }

    private Node createFactorNode(Factor element) {
        String name = element.getFullName();

        final FactorNode node = new FactorNode(name, element.getIdentifier());
        if (!element.getAnnotations().isEmpty() && element.hasAggregationAnnotation()) {
            node.setMethod(element.getAggregationAnnotationValue());
        } else {
            node.setMethod(FactorMethod.MEAN);
        }

        return node;
    }

    private Node createProviderNode(MeasurementMethod element) {
        Node node;
        String repo = "";
        if (element instanceof ManualInstrument) {
            repo = ValueNode.MANUAL;
        } else if (element instanceof ToolBasedInstrument) {
            repo = ((ToolBasedInstrument) element).getTool().getName();
        }

        MeasureType type = null;
        if (element.getDetermines() != null) {
            final Measure determines = element.getDetermines();

            type = determines.getType();
        }

        if (type == MeasureType.FINDINGS) {
            node = new FindingsUnionNode(element.getName(), element.getIdentifier());
        } else {
            node = new ValueNode(element.getName(), element.getIdentifier(), repo);
        }

        return node;
    }

    private Node createMeasureNode(Measure element) {
        MeasureNode node;

        if (element.isNormalizer()) {
            node = new NormalizationNode(element.getFullName(), element.getIdentifier());
            setMeasureNodeProperties(element, node);
        } else {
            node = new MeasureNode(element.getFullName(), element.getIdentifier());
            setMeasureNodeProperties(element, node);
        }

        return node;
    }

    /**
     * Sets a given MeasureNode's properties.
     *
     * @param measure Measure the MeasureNode represents
     * @param node    Node for which properties will be set.
     */
    @VisibleForTesting
    void setMeasureNodeProperties(final Measure measure, final MeasureNode node) {
        // TODO Need to add a field to qm files in order to specify the
        // MeasureMethod correctly
        node.setType(measure.getType());
        if (measure.getType().equals(MeasureType.FINDINGS))
            node.setMethod(MeasureMethod.UNION);
        else
            node.setMethod(MeasureMethod.MEAN);
    }
}
