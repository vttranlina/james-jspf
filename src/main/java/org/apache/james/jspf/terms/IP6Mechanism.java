/***********************************************************************
 * Copyright (c) 2006 The Apache Software Foundation.             *
 * All rights reserved.                                                *
 * ------------------------------------------------------------------- *
 * Licensed under the Apache License, Version 2.0 (the "License"); you *
 * may not use this file except in compliance with the License. You    *
 * may obtain a copy of the License at:                                *
 *                                                                     *
 *     http://www.apache.org/licenses/LICENSE-2.0                      *
 *                                                                     *
 * Unless required by applicable law or agreed to in writing, software *
 * distributed under the License is distributed on an "AS IS" BASIS,   *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or     *
 * implied.  See the License for the specific language governing       *
 * permissions and limitations under the License.                      *
 ***********************************************************************/

package org.apache.james.jspf.terms;

import org.apache.james.jspf.util.Inet6Util;

/**
 * This class represent the ip6 mechanism
 * 
 */
public class IP6Mechanism extends IP4Mechanism {

    /**
     * ABNF: IP6 = "ip6" ":" ip6-network [ ip6-cidr-length ]
     */
    public static final String REGEX = "[iI][pP][6]"
            + "\\:([0-9A-Fa-f\\:\\.]+)" + "(?:" + IP6_CIDR_LENGTH_REGEX + ")?";

    /**
     * @see org.apache.james.jspf.terms.IP4Mechanism#isValidAddress(String)
     */
    protected boolean isValidAddress(String ipString) {
        return Inet6Util.isValidIP6Address(ipString);
    }

    /**
     * @see org.apache.james.jspf.terms.IP4Mechanism#getMaxCidr()
     */
    protected int getMaxCidr() {
        return 128;
    }
}
