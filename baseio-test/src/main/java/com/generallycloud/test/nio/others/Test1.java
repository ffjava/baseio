/*
 * Copyright 2015-2017 GenerallyCloud.com
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.generallycloud.test.nio.others;

import com.generallycloud.baseio.common.MathUtil;

//一共有7*3=21个格子，要随机选取其中16个格子，一共有多少种可能，用程序表示出来
public class Test1 {

    static int size;

    public static void main(String[] args) {

        byte[] bb = new byte[] { 0, 0, 8, 98, 64, 7, 81, 85, 69, 85, 69, 95, 49, 0, 0, 4, 3, -128,
                0, 0, 2, 96, 0, 7, 81, 85, 69, 85, 69, 95, 49, 9, 77, 101, 115, 115, 97, 103, 101,
                73, 100, 96, 3, -24, 56, 52, 107, 102, 107, 55, 100, 56, 116, 99, 120, 121, 108, 53,
                121, 48, 106, 119, 101, 116, 102, 120, 115, 104, 48, 51, 118, 111, 53, 55, 109, 53,
                53, 97, 114, 101, 102, 119, 48, 52, 110, 100, 109, 98, 115, 52, 103, 99, 118, 114,
                49, 119, 55, 48, 99, 110, 104, 119, 113, 122, 100, 117, 115, 118, 122, 117, 115,
                102, 119, 56, 119, 53, 99, 114, 106, 116, 105, 51, 50, 56, 114, 108, 116, 48, 57,
                98, 54, 49, 53, 102, 49, 103, 56, 115, 49, 116, 110, 103, 51, 99, 112, 54, 53, 115,
                50, 112, 50, 48, 122, 117, 103, 110, 104, 117, 98, 110, 114, 122, 117, 117, 114, 99,
                120, 57, 48, 54, 50, 121, 55, 54, 115, 116, 108, 104, 104, 48, 100, 122, 102, 103,
                55, 114, 56, 103, 103, 104, 107, 119, 108, 100, 116, 110, 122, 111, 112, 116, 116,
                109, 50, 111, 51, 118, 54, 55, 97, 103, 50, 121, 48, 53, 110, 112, 97, 103, 53, 118,
                57, 48, 122, 121, 53, 57, 109, 114, 109, 109, 57, 122, 57, 112, 113, 101, 51, 105,
                53, 49, 97, 110, 115, 119, 110, 54, 107, 48, 49, 51, 100, 51, 105, 120, 100, 115,
                121, 55, 120, 56, 105, 106, 103, 101, 109, 122, 56, 101, 99, 52, 97, 111, 99, 115,
                54, 51, 53, 115, 119, 106, 99, 117, 103, 57, 109, 54, 54, 107, 50, 103, 111, 100,
                98, 120, 98, 53, 121, 53, 99, 106, 111, 49, 49, 121, 119, 104, 104, 55, 98, 52, 114,
                106, 108, 102, 99, 57, 104, 102, 118, 51, 50, 117, 112, 52, 100, 117, 109, 49, 122,
                113, 101, 112, 114, 121, 122, 114, 97, 113, 52, 50, 109, 97, 110, 108, 116, 120,
                120, 52, 106, 119, 113, 48, 109, 110, 103, 114, 114, 114, 115, 53, 108, 51, 122,
                114, 107, 120, 48, 53, 52, 51, 103, 114, 115, 51, 48, 107, 121, 120, 122, 52, 55,
                116, 114, 110, 48, 105, 104, 98, 116, 101, 112, 111, 102, 122, 111, 115, 100, 120,
                112, 101, 48, 108, 52, 106, 101, 56, 113, 106, 104, 113, 51, 121, 117, 117, 49, 116,
                101, 51, 99, 111, 112, 108, 103, 54, 100, 113, 119, 50, 50, 52, 101, 52, 111, 118,
                107, 113, 98, 102, 107, 48, 114, 52, 114, 116, 55, 99, 53, 56, 102, 51, 54, 105,
                105, 104, 55, 98, 99, 51, 120, 103, 107, 51, 101, 101, 105, 48, 110, 52, 53, 119,
                51, 113, 119, 106, 98, 103, 115, 109, 101, 105, 105, 107, 121, 101, 119, 102, 104,
                121, 104, 98, 106, 106, 51, 49, 52, 120, 120, 102, 56, 101, 107, 117, 48, 97, 57,
                107, 108, 49, 98, 120, 120, 50, 104, 109, 117, 52, 107, 109, 52, 121, 105, 117, 98,
                111, 51, 115, 107, 117, 57, 115, 50, 52, 122, 51, 52, 117, 121, 50, 101, 112, 110,
                106, 105, 49, 97, 53, 55, 97, 108, 120, 57, 55, 50, 57, 100, 52, 116, 120, 52, 118,
                109, 122, 120, 117, 111, 108, 52, 52, 122, 49, 51, 103, 103, 122, 114, 99, 97, 51,
                97, 107, 122, 122, 112, 98, 120, 101, 116, 99, 115, 122, 115, 53, 117, 118, 121, 99,
                114, 120, 117, 106, 51, 102, 55, 54, 121, 118, 110, 49, 50, 100, 102, 52, 119, 116,
                49, 51, 57, 101, 110, 122, 51, 57, 57, 98, 112, 107, 122, 103, 122, 117, 120, 98,
                103, 99, 119, 57, 97, 121, 112, 57, 115, 55, 104, 122, 48, 52, 56, 104, 50, 110,
                110, 109, 50, 112, 97, 57, 117, 110, 101, 56, 103, 50, 104, 120, 99, 102, 52, 57,
                108, 115, 97, 122, 117, 56, 114, 49, 102, 117, 117, 106, 97, 100, 57, 105, 112, 50,
                56, 122, 113, 99, 120, 115, 122, 115, 102, 107, 110, 107, 107, 48, 117, 48, 116,
                102, 52, 120, 101, 117, 105, 103, 103, 112, 55, 114, 108, 112, 107, 48, 49, 101, 54,
                113, 105, 109, 51, 50, 108, 101, 119, 107, 48, 111, 111, 102, 112, 56, 100, 122, 53,
                51, 109, 104, 108, 97, 105, 122, 48, 110, 107, 104, 104, 112, 109, 99, 114, 116, 50,
                117, 108, 51, 115, 117, 49, 122, 55, 105, 48, 120, 52, 122, 99, 49, 54, 100, 49,
                117, 50, 117, 103, 122, 121, 103, 105, 105, 114, 57, 53, 99, 116, 56, 56, 48, 109,
                112, 120, 116, 116, 111, 53, 113, 111, 115, 98, 121, 48, 51, 116, 106, 52, 118, 103,
                53, 119, 56, 120, 103, 122, 48, 110, 110, 57, 109, 102, 56, 52, 54, 101, 97, 117,
                121, 110, 108, 48, 118, 53, 105, 109, 110, 121, 114, 121, 110, 101, 108, 106, 57,
                116, 99, 99, 52, 51, 56, 102, 119, 104, 119, 122, 117, 54, 116, 120, 111, 50, 120,
                97, 116, 48, 108, 103, 56, 118, 99, 48, 114, 53, 53, 122, 104, 110, 52, 112, 104,
                104, 54, 101, 97, 50, 107, 57, 114, 108, 118, 118, 50, 119, 121, 99, 105, 97, 110,
                49, 118, 115, 118, 109, 103, 108, 49, 52, 114, 117, 98, 106, 55, 48, 54, 101, 122,
                112, 122, 48, 118, 122, 122, 114, 57, 100, 114, 48, 118, 48, 104, 100, 103, 105,
                119, 114, 56, 101, 50, 107, 56, 55, 49, 97, 110, 97, 51, 98, 119, 98, 102, 119, 49,
                103, 100, 49, 53, 113, 50, 105, 51, 101, 104, 109, 112, 110, 115, 104, 107, 51, 104,
                56, 57, 120, 117, 56, 109, 100, 97, 99, 98, 101, 55, 108, 48, 101, 117, 101, 51,
                108, 111, 120, 101, 110, 54, 115, 103, 112, 100, 102, 107, 102, 113, 53, 100, 109,
                53, 111, 100, 54, 99, 54, 50, 118, 110, 48, 57, 104, 102, 98, 101, 116, 98, 111,
                121, 100, 98, 110, 98, 105, 54, 51, 101, 52, 118, 121, 51, 111, 119, 120, 121, 106,
                0, 0, 0, 98, 10, 80, 82, 79, 95, 79, 70, 70, 83, 69, 84, 96, 0, 12, 80, 82, 79, 68,
                85, 67, 69, 82, 52, 95, 54, 50, 10, 105, 110, 106, 101, 99, 116, 95, 118, 102, 107,
                96, 0, 10, 120, 50, 121, 116, 114, 56, 110, 97, 117, 122, 10, 105, 110, 106, 101,
                99, 116, 95, 98, 101, 48, 96, 0, 10, 120, 50, 121, 116, 114, 56, 110, 113, 116, 99,
                10, 105, 110, 106, 101, 99, 116, 95, 108, 98, 117, 96, 0, 10, 120, 50, 121, 116,
                114, 56, 110, 48, 113, 57, 0, 0, 3, -24, 107, 103, 122, 117, 122, 106, 115, 107, 56,
                114, 99, 100, 48, 104, 100, 99, 121, 98, 116, 56, 117, 99, 55, 119, 99, 102, 97, 51,
                104, 106, 49, 104, 104, 112, 54, 116, 117, 98, 99, 103, 50, 115, 49, 113, 55, 103,
                118, 114, 97, 54, 100, 98, 51, 106, 99, 114, 50, 119, 98, 53, 101, 115, 57, 55, 97,
                101, 57, 55, 117, 98, 107, 98, 104, 114, 54, 121, 56, 120, 102, 101, 107, 54, 48,
                56, 99, 108, 113, 105, 100, 104, 117, 100, 118, 107, 55, 100, 56, 50, 118, 102, 114,
                52, 105, 104, 55, 101, 52, 101, 99, 101, 57, 118, 50, 119, 57, 113, 50, 54, 101, 57,
                57, 54, 114, 99, 108, 99, 105, 101, 100, 106, 105, 55, 56, 48, 119, 119, 99, 115,
                101, 117, 118, 106, 54, 107, 118, 118, 119, 122, 98, 48, 115, 56, 50, 101, 51, 52,
                56, 56, 49, 101, 51, 102, 97, 105, 106, 112, 118, 101, 100, 99, 104, 50, 52, 112,
                118, 104, 97, 108, 99, 101, 100, 104, 108, 49, 54, 49, 49, 108, 101, 108, 52, 53,
                116, 102, 120, 104, 100, 112, 50, 55, 98, 50, 105, 122, 99, 100, 102, 115, 102, 120,
                99, 115, 55, 100, 106, 99, 107, 120, 121, 118, 116, 49, 101, 107, 116, 114, 103,
                112, 51, 114, 55, 105, 102, 104, 55, 98, 121, 114, 57, 118, 108, 49, 105, 105, 122,
                101, 118, 51, 115, 113, 99, 113, 104, 100, 104, 114, 121, 51, 100, 100, 100, 98,
                119, 119, 106, 113, 103, 54, 121, 48, 117, 114, 108, 119, 117, 97, 102, 101, 57, 52,
                103, 115, 57, 49, 100, 101, 53, 116, 52, 54, 100, 101, 54, 112, 53, 103, 101, 49,
                112, 50, 48, 56, 99, 99, 103, 121, 98, 53, 99, 49, 50, 118, 54, 54, 54, 55, 104, 48,
                102, 101, 54, 122, 99, 99, 104, 103, 102, 118, 54, 55, 102, 99, 122, 100, 99, 101,
                103, 106, 56, 54, 50, 99, 120, 119, 113, 56, 116, 52, 51, 117, 101, 51, 55, 115, 99,
                52, 116, 99, 48, 103, 121, 116, 107, 53, 121, 119, 53, 102, 100, 57, 57, 100, 56,
                116, 102, 114, 51, 52, 48, 118, 105, 115, 53, 98, 101, 101, 103, 116, 103, 51, 97,
                122, 53, 113, 117, 122, 99, 54, 103, 54, 56, 106, 114, 104, 107, 117, 102, 105, 120,
                120, 119, 106, 113, 114, 102, 99, 118, 122, 102, 116, 116, 120, 99, 50, 103, 104,
                98, 102, 53, 98, 121, 113, 118, 55, 49, 116, 120, 120, 122, 100, 116, 98, 117, 119,
                100, 119, 113, 121, 121, 102, 100, 103, 99, 99, 117, 107, 116, 122, 57, 99, 112,
                108, 122, 48, 100, 113, 99, 99, 101, 119, 49, 57, 103, 122, 49, 103, 100, 120, 57,
                113, 51, 102, 55, 122, 57, 108, 55, 101, 103, 101, 102, 103, 57, 100, 101, 116, 52,
                50, 121, 120, 100, 112, 104, 106, 112, 48, 99, 108, 106, 101, 108, 115, 103, 56, 99,
                103, 97, 49, 101, 99, 57, 51, 48, 103, 103, 101, 100, 102, 118, 118, 101, 54, 114,
                112, 102, 112, 122, 101, 101, 52, 113, 99, 116, 56, 114, 55, 101, 55, 104, 57, 97,
                100, 114, 54, 99, 57, 121, 102, 117, 106, 105, 100, 97, 50, 100, 101, 115, 117, 103,
                98, 56, 100, 102, 108, 116, 50, 101, 102, 108, 108, 113, 52, 122, 101, 118, 101, 57,
                99, 113, 118, 114, 98, 108, 112, 100, 52, 108, 55, 106, 119, 101, 99, 103, 107, 119,
                101, 50, 50, 49, 101, 52, 112, 108, 57, 50, 116, 107, 118, 101, 119, 99, 114, 117,
                103, 108, 48, 55, 112, 101, 57, 107, 54, 100, 117, 57, 57, 121, 112, 115, 108, 120,
                52, 101, 107, 101, 53, 114, 99, 55, 101, 55, 117, 122, 50, 122, 122, 99, 57, 99, 56,
                117, 103, 99, 116, 57, 120, 118, 118, 52, 106, 54, 48, 52, 122, 99, 100, 116, 105,
                53, 120, 49, 102, 101, 48, 116, 98, 122, 99, 51, 51, 117, 52, 107, 115, 101, 104,
                102, 49, 119, 48, 112, 120, 101, 99, 50, 122, 119, 119, 52, 49, 114, 54, 56, 101,
                57, 48, 102, 55, 57, 100, 101, 106, 120, 99, 99, 103, 101, 114, 100, 105, 115, 100,
                57, 101, 57, 118, 101, 100, 118, 120, 120, 54, 108, 104, 114, 56, 107, 107, 99, 49,
                52, 99, 56, 56, 51, 104, 53, 51, 55, 113, 100, 99, 102, 56, 121, 103, 97, 118, 104,
                98, 107, 99, 118, 101, 99, 50, 50, 108, 49, 117, 107, 103, 105, 116, 112, 57, 100,
                50, 48, 99, 97, 104, 120, 49, 50, 100, 54, 100, 50, 116, 48, 121, 108, 56, 114, 114,
                103, 102, 107, 117, 98, 119, 98, 101, 57, 105, 56, 99, 51, 101, 99, 112, 56, 99, 48,
                118, 107, 97, 114, 99, 54, 104, 104, 101, 119, 50, 103, 52, 119, 119, 105, 116, 112,
                101, 122, 108, 54, 48, 97, 97, 101, 98, 100, 114, 120, 112, 50, 100, 97, 55, 97, 49,
                118, 48, 100, 103, 54, 57, 113, 121, 106, 99, 105, 116, 101, 52, 101, 99, 97, 101,
                101, 54, 108, 115, 54, 99, 97, 99, 119, 115, 118, 120, 98, 54, 107, 116, 101, 122,
                107, 106, 100, 112, 50, 112, 102, 113, 98, 113, 117, 98, 100, 118, 115, 100, 104,
                53, 101, 120, 102, 116, 119, 49, 52, 50, 55, 119, 122, 102, 119, 107, 108, 99, 57,
                107, 49, 115, 112, 114, 113, 116, 106, 48, 99, 116, 57, 116, 102, 48, 51, 99, 116,
                50, 105, 55, 118, 52, 115, 117, 122, 117, 53, 104, 115, 49, 104, 51, 115, 105, 114,
                105, 101, 97, 50, 99, 108, 119, 117, 113, 116, 56, 113, 51, 100, 115, 113, 50, 113,
                120, 105, 102, 116, 103, 97, 100, 102, 51, 98, 99, 100, 121, 0, 0, 0, 118, 64, 7,
                81, 85, 69, 85, 69, 95, 49 };

        System.out.println(MathUtil.byte2Int(bb, 0));

    }

    static void assembly(StringBuilder src, int length) {

        int remain = src.length() - length;

        for (int i = 0; i < remain; i++) {

            int start = i;

            int end = length + i + 1;

            String s = src.substring(start, end);

            StringBuilder b2 = new StringBuilder(s);

            int len = b2.length();

            int j = (i + 1) % 2;

            for (; j < len; j++) {

                StringBuilder b2_copy = new StringBuilder(b2);

                b2_copy.deleteCharAt(j);

                assembly(null, b2_copy);
            }
        }
    }

    static void assembly(StringBuilder appender, StringBuilder remaining) {

        for (int i = 0; i < remaining.length(); i++) {

            StringBuilder _appender;

            if (appender == null) {

                _appender = new StringBuilder();

            } else {

                _appender = new StringBuilder(appender);
            }

            StringBuilder _remaining = new StringBuilder(remaining.toString());

            char c = _remaining.charAt(i);

            _appender.append(c);

            _remaining.deleteCharAt(i);

            if (_remaining.length() > 0) {

                assembly(_appender, _remaining);

            } else {

                System.out.println(_appender);

                size++;

                //				if (size % 1000000 == 0) {
                //					System.out.println(size);
                //				}
            }
        }
    }
}
