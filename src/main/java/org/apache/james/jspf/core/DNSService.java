/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/


package org.apache.james.jspf.core;

import org.apache.james.jspf.exceptions.PermErrorException;
import org.apache.james.jspf.exceptions.TempErrorException;

import java.util.List;

/**
 * Interface which should be used to access all necassary DNS-Records
 *  
 */
public interface DNSService {
    
    /** The record types for the lookups */
    public int A = 1;
    public int AAAA = 2;
    public int MX = 3;
    public int PTR = 4;
    public int TXT = 5;
    public int SPF = 6;

    /**
     * Get the SPF-Record for a server given it's version
     * 
     * TODO: support SPF Records too. This will be done if dnsjava support it!
     * 
     * @param hostname
     *            The hostname for which we want to retrieve the SPF-Record
     * @param spfVersion
     *            The SPF-Version which should used.
     * @return The SPF-Record if one is found.
     * @throws PermErrorException
     *             if more then one SPF-Record was found.
     * @throws TempErrorException
     *             if the lookup result was "TRY_AGAIN"
     */
    public String getSpfRecord(String hostname, String spfVersion)
            throws PermErrorException, TempErrorException;

    /**
     * Get a list of IPAddr's for a server
     * 
     * @param strServer
     *            The hostname or ipAddress whe should get the A-Records for
     * @return The ipAddresses
     * @throws PermErrorException
     *             if an PermError should be returned
     * @throws TempErrorException
     *             if the lookup result was "TRY_AGAIN"
     */
    public List getARecords(String strServer) throws PermErrorException, TempErrorException;

    /**
     * Get a list of IPAddr's for a server
     * 
     * @param strServer
     *            The hostname or ipAddress whe should get the AAAA-Records for
     * @return The ipAddresses
     * @throws PermErrorException
     *             if an PermError should be returned
     * @throws TempErrorException
     *             if the lookup result was "TRY_AGAIN"
     */
    public List getAAAARecords(String strServer)
            throws PermErrorException, TempErrorException;

    /**
     * Get TXT records as a string
     * 
     * @param strServer
     *            The hostname for which we want to retrieve the TXT-Record
     * @return String which reflect the TXT-Record
     * @throws PermErrorException
     *             if the hostname is not resolvable
     * @throws TempErrorException
     *             if the lookup result was "TRY_AGAIN"
     */
    public String getTxtCatType(String strServer) throws PermErrorException, TempErrorException;

    /**
     * Get reverse DNS records
     * 
     * @param ipAddress
     *            The ipAddress for which we want to get the PTR-Record
     * @return the PTR-Records
     * @throws PermErrorException
     *             if an PermError should be returned
     * @throws TempErrorException
     *             if the lookup result was "TRY_AGAIN"
     */

    public List getPTRRecords(String ipAddress) throws PermErrorException,
            TempErrorException;

    /**
     * Get a list of masked IPAddr MX-Records
     * 
     * @param domainName
     *            The domainName or ipaddress we want to get the ips for
     * @return IPAddresses of the MX-Records
     * @throws PermErrorException
     *             if an PermError should be returned
     * @throws TempErrorException
     *             if the lookup result was "TRY_AGAIN"
     */
    public List getMXRecords(String domainName)
            throws PermErrorException, TempErrorException;

    /**
     * Try to get all domain names for the running host
     * 
     * @return names A List contains all domain names which could resolved
     */
    public List getLocalDomainNames();

    /**
     * Set the timeout for DNS-Requests
     * 
     * @param timeOut
     *            The timeout in seconds
     */
    public void setTimeOut(int timeOut);
    
    /**
     * @return the current record limit
     */
    public int getRecordLimit();

    /**
     * Sets a new limit for the number of records for MX and PTR lookups.
     * @param recordLimit the new limit (0 => unlimited)
     */
    public void setRecordLimit(int recordLimit);

}