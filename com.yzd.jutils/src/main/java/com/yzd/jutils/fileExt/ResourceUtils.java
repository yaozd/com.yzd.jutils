/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yzd.jutils.fileExt;

import com.google.common.io.Resources;

import java.io.File;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public final class ResourceUtils {
    /**
     * 标准地址
     * @param resourcePath
     * @return
     */
    public static String toPath(String resourcePath) {
        try {
            return new File(Resources.getResource(resourcePath).toURI()).getCanonicalPath();
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        //正确
        System.out.println(ResourceUtils.toPath("version.properties"));
        //错误
        System.out.println(ResourceUtils.toPath("/version.properties"));
    }
}
