/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.basistech.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;


/**
 * Compare scans.
 */
@State(Scope.Thread)
public class ScanBenchmark {
    private final static int DIM = 256;
    private double[][][] multiDoubles;
    private Matrix3 matrix3;

    public ScanBenchmark() {
        multiDoubles = new double[DIM][DIM][DIM];
        for (int x = 0; x < multiDoubles.length; x++) {
            double[][] d2 = multiDoubles[x];
            for (int y = 0; y < d2.length; y++) {
                double[] d3 = d2[y];
                for (int z = 0; z < d3.length; z++) {
                    d3[z] = x * y * z;
                }
            }
        }
        matrix3 = new Matrix3(DIM, DIM, DIM);
        for (int x = 0; x < matrix3.d1(); x++) {
            for (int y = 0; y < matrix3.d2(); y++) {
                for (int z = 0; z < matrix3.d3(); z++) {
                    matrix3.set(x, y, z, x * y * z);
                }
            }
        }
    }
    @Benchmark
    public double multiArrayScan() {
        double sum = 0;
        for (int x = 0; x < DIM; x++) {
            for (int y = 0; y < DIM; y++) {
                for (int z = 0; z < DIM; z++) {
                    sum += multiDoubles[x][y][z];
                }
            }
        }
        return sum;
    }

    @Benchmark
    public double flattenedArrayScan() {
        double sum = 0;
        for (int x = 0; x < matrix3.d1(); x++) {
            for (int y = 0; y < matrix3.d2(); y++) {
                for (int z = 0; z < matrix3.d3(); z++) {
                    sum += matrix3.get(x, y, z);
                }
            }
        }
        return sum;
    }
}
