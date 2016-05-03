package com.wuyouz.playground.ml;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Viterbi DP Algorithm
 * https://www.wikiwand.com/en/Viterbi_algorithm
 * <p>
 * Created by dqliu on 4/30/16.
 */
public class ViterbiDP {
    private String[] obs;
    private String[] states;
    private Table<String, String, Double> trans;
    private Table<String, String, Double> emit;

    private static class VPair {
        private String state;
        private double p;

        VPair(String state, double p) {
            this.state = state;
            this.p = p;
        }

        public String getState() {
            return state;
        }

        public double getP() {
            return p;
        }

        @Override
        public String toString() {
            return String.format("(%s,%f)", state, p);
        }
    }


    public ViterbiDP(String[] obs, String[] states,
                     Table<String, String, Double> trans, Table<String, String, Double> emit) {
        this.obs = obs;
        this.states = states;
        this.trans = trans;
        this.emit = emit;
    }

    List<String> mostPossibleStates(VPair[] startP, String[] Y) {
        VPair[][] V = new VPair[Y.length][states.length];

        for (int i = 0; i < states.length; i++) {
            V[0][i] = new VPair(states[i],
                    startP[i].getP() * emit.get(startP[i].getState(), Y[0]).doubleValue());
        }
        System.out.println(Arrays.toString(V[0]));

        for (int i = 1; i < Y.length; i++) {
            for (int j = 0; j < states.length; j++) {
                double maxProb = 0;
                for (int k = 0; k < states.length; k++) {
                    double temp = V[i - 1][k].getP() * trans.get(V[i - 1][k].getState(), states[j]).doubleValue()
                            * emit.get(states[j], Y[i]).doubleValue();
                    if (temp > maxProb) {
                        maxProb = temp;
                    }
                }
                V[i][j] = new VPair(states[j], maxProb);
            }
            System.out.println(Arrays.toString(V[i]));
        }

        List<String> result = new ArrayList<>();
        for (int i = V.length - 1; i >= 0; i--) {
            double maxProb = 0;
            String state = "";
            for (VPair pair : V[i]) {
                if (pair.getP() > maxProb) {
                    state = pair.state;
                    maxProb = pair.getP();
                }
            }
            result.add(state);
        }

        return Lists.reverse(result);
    }

    public static void main(String[] args) {
        String[] states = {"Healthy", "Fever"};
        String[] obs = {"normal", "cold", "dizzy"};
        VPair[] startP = {new VPair("Healthy", 0.6), new VPair("Fever", 0.4)};
        Table<String, String, Double> trans = HashBasedTable.create();
        trans.put("Healthy", "Healthy", 0.7);
        trans.put("Healthy", "Fever", 0.3);
        trans.put("Fever", "Healthy", 0.4);
        trans.put("Fever", "Fever", 0.6);

        Table<String, String, Double> emits = HashBasedTable.create();
        emits.put("Healthy", "normal", 0.5);
        emits.put("Healthy", "cold", 0.4);
        emits.put("Healthy", "dizzy", 0.1);
        emits.put("Fever", "normal", 0.1);
        emits.put("Fever", "cold", 0.3);
        emits.put("Fever", "dizzy", 0.6);

        ViterbiDP dp = new ViterbiDP(obs, states, trans, emits);
        System.out.println(dp.mostPossibleStates(startP, new String[]{"normal", "cold", "dizzy"}));

    }

}
