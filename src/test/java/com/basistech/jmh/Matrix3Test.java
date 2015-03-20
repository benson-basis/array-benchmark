/******************************************************************************
 ** This data and information is proprietary to, and a valuable trade secret
 ** of, Basis Technology Corp.  It is given in confidence by Basis Technology
 ** and may only be used as permitted under the license agreement under which
 ** it has been distributed, and in no other way.
 **
 ** Copyright (c) 2015 Basis Technology Corporation All rights reserved.
 **
 ** The technical data and information provided herein are provided with
 ** `limited rights', and the computer software provided herein is provided
 ** with `restricted rights' as those terms are defined in DAR and ASPR
 ** 7-104.9(a).
 ******************************************************************************/

package com.basistech.jmh;

import org.junit.Test;

/**
 *
 */
public class Matrix3Test {
    @Test
    public void indices() {
        Matrix3 doubles = new Matrix3(10, 10, 10);
        for (int x = 0; x < doubles.d1(); x++) {
            for (int y = 0; y < doubles.d2(); y++) {
                for (int z = 0; z < doubles.d3(); z++) {
                    doubles.set(x, y, z, x * y * z);
                }
            }
        }
    }
}
