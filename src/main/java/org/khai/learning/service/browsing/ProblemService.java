package org.khai.learning.service.browsing;

import org.khai.learning.engine.math.expression.impl.Fraction;
import org.khai.learning.engine.math.expression.impl.Polynomial;
import org.khai.learning.engine.problem.characteristic.transitional.TransitionalCharacteristic;
import org.khai.learning.engine.problem.characteristic.transitional.context.LaplasContext;
import org.khai.learning.engine.problem.characteristic.transitional.context.TransitionalContext;
import org.khai.learning.service.model.LaplasContextModel;
import org.khai.learning.service.model.TransitionalContextModel;
import org.springframework.stereotype.Component;

@Component
public class ProblemService {

    public TransitionalContextModel getNew() {
        TransitionalCharacteristic problem = new TransitionalCharacteristic(generate());
        TransitionalContext solutionContext = problem.solve();
        return convert(solutionContext);
    }

    private TransitionalContext generate() {
        return new TransitionalContext()
                .setWpe(3.2).setWpp(2.6)
                .setKdu(10.5).setKdw(-8.2e3)
                .setT1(0.15).setT2(0.003)
                .setWtg(0.08).setWke(1)
                .setUz(1.5).setMn(1e-2);
    }

    private TransitionalContextModel convert(TransitionalContext context) {
        TransitionalContextModel model = new TransitionalContextModel();
        model.setWpe(context.getWpe());
        model.setWpp(context.getWpp());
        model.setKdu(context.getKdu());
        model.setKdw(context.getKdw());
        model.setT1(context.getT1());
        model.setT2(context.getT2());
        model.setWtg(context.getWtg());
        model.setWke(context.getWke());
        model.setUz(context.getUz());
        model.setMn(context.getMn());

        Fraction phiS = (Fraction) context.getfZd();
        model.setPhiZdNum(((Polynomial) phiS.getNum()).getCoef(0));
        Fraction phifS = (Fraction) context.getfZb();
        model.setPhiZbNum(((Polynomial) phifS.getNum()).getCoef(0));

        Polynomial charEquation = context.getCharEquation();
        model.setCharEquationC(charEquation.getCoef(0));
        model.setCharEquationB(charEquation.getCoef(1));
        model.setCharEquationA(charEquation.getCoef(2));

        model.setCharRoot1(context.getCharRoot1());
        model.setCharRoot2(context.getCharRoot2());
        model.setTz1(context.getTz1());
        model.setTz2(context.getTz2());

        model.setLaplasZd(convert(context.getLaplasZd()));
        model.setLaplasZb(convert(context.getLaplasZb()));

        model.setT(context.getT());
        model.setUtzd(context.getUtzd());
        model.setUtzb(context.getUtzb());

        model.setStable(context.isStable());

        model.setStableTzd(context.getStableTzd());
        model.setStableTzb(context.getStableTzb());

        model.setEpsZd(context.getEpsZd());
        model.setEpsZb(context.getEpsZb());

        model.setSigmaZd(context.getSigmaZd());
        model.setSigmaZb(context.getSigmaZb());

        return model;
    }

    private LaplasContextModel convert(LaplasContext context) {
        LaplasContextModel model = new LaplasContextModel();
        model.setA(context.getA());
        model.setB(context.getB());
        model.setC(context.getC());
        model.setOuterCoef(context.getOuterCoef());
        model.setCoef0(context.getCoef0());
        model.setCoef1(context.getCoef1());
        model.setCoef2(context.getCoef2());
        model.setAlpha1(context.getAlpha1());
        model.setAlpha2(context.getAlpha2());
        return model;
    }
}
