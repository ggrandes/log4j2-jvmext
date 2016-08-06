/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.javastack.log4j.jvmext;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.MapLookup;
import org.apache.logging.log4j.core.lookup.StrLookup;
import org.apache.logging.log4j.core.util.NetUtils;

@Plugin(name = "jvmext", category = StrLookup.CATEGORY)
public class JvmextLookup extends MapLookup {
	private static final RuntimeMXBean jmx = ManagementFactory.getRuntimeMXBean();

	public JvmextLookup() {
		super(new HashMap<String, String>());
		put("node", NetUtils.getLocalHostname());
		put("pid", String.valueOf(getProcessId(0)));
		put("startTime", String.valueOf(jmx.getStartTime()));
	}

	private final void put(final String key, final String value) {
		getMap().put(key, value);
	}

	/**
	 * Return the PID of running JVM process.
	 * <a href="http://stackoverflow.com/questions/35842">StackOverflow#35842</a>
	 * 
	 * @param fallback
	 * @return processid or fallback if unknown
	 */
	private static long getProcessId(final long fallback) {
		final String jvmName = jmx.getName();
		final int index = jvmName.indexOf('@');
		if (index < 1) {
			return fallback;
		}
		try {
			return Long.parseLong(jvmName.substring(0, index));
		} catch (NumberFormatException ignore) {
		}
		return fallback;
	}
}
