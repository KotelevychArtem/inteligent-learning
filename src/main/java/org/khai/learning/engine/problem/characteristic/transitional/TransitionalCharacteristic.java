package org.khai.learning.engine.problem.characteristic.transitional;

import org.khai.learning.engine.problem.Step;
import org.khai.learning.engine.problem.characteristic.transitional.context.LaplasContext;
import org.khai.learning.engine.problem.characteristic.transitional.context.TransitionalContext;
import org.khai.learning.engine.problem.characteristic.transitional.step.*;

import java.util.Arrays;
import java.util.List;

public class TransitionalCharacteristic {

    private final TransitionalContext context;
    private List<Step> steps;

    public TransitionalCharacteristic(TransitionalContext context) {
        this.context = context;
        context.setLaplasZd(new LaplasContext())
                .setLaplasZb(new LaplasContext());
        steps = Arrays.asList(
                new TransferZdStep(context),
                new TransferZbSetp(context),
                new CharacteristicEquationStep(context),
                new LaplasStep(context, TransitionalContext::getLaplasZd),
                new LaplasStep(context, TransitionalContext::getLaplasZb),
                new PlotStep(context),
                new SystemCharacteristicStep(context)
        );
    }

    public TransitionalContext solve() {
        steps.forEach(Step::getAnswer);
        return context;
    }

    // TODO: test
    public static void main(String[] args) {
        TransitionalContext context = new TransitionalContext()
                .setWpe(3.2).setWpp(2.6)
                .setKdu(10.5).setKdw(-8.2e3)
                .setT1(0.15).setT2(0.003)
                .setWtg(0.08).setWke(1)
                .setUz(1.5).setMn(1e-2);
        TransitionalCharacteristic problem = new TransitionalCharacteristic(context);
        problem.solve();
        System.out.println(Arrays.toString(context.getUtzd()));
    }
}
