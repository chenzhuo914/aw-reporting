// Copyright 2014 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.ads.adwords.jaxws.extensions.processors.onmemory;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.google.api.ads.adwords.jaxws.extensions.report.model.csv.AnnotationBasedMappingStrategy;
import com.google.api.ads.adwords.jaxws.extensions.report.model.entities.Report;
import com.google.api.ads.adwords.jaxws.extensions.report.model.entities.ReportAccount;
import com.google.api.ads.adwords.jaxws.extensions.report.model.persistence.EntityPersister;
import com.google.api.ads.adwords.jaxws.extensions.report.model.util.ModifiedCsvToBean;
import com.google.api.ads.adwords.jaxws.extensions.util.FileUtil;
import com.google.api.ads.adwords.lib.jaxb.v201402.ReportDefinitionDateRangeType;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponseException;
import com.google.api.ads.adwords.lib.utils.ReportException;
import com.google.api.ads.common.lib.exception.OAuthException;
import com.google.api.ads.common.lib.exception.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import au.com.bytecode.opencsv.bean.MappingStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Test case for the {@link RunnableProcessorOnMemory} class.
 *
 * @author jtoledo@google.com (Julian Toledo)
 */
public class RunnableProcessorOnMemoryTest {

  private static final String CSV_FILE_PATH =
      "src/test/resources/csv/reportDownload-ACCOUNT_PERFORMANCE_REPORT-2602198216-1370030134500.report";

  @Mock
  private EntityPersister mockedEntitiesPersister;

  @Spy
  private RunnableProcessorOnMemory<ReportAccount> runnableProcessorOnMemory;
  
  @Captor
  ArgumentCaptor<List<? extends Report>> reportEntitiesCaptor;

  @Before
  public void setUp() throws OAuthException, IOException, ValidationException,
  ReportException, ReportDownloadResponseException {

    ModifiedCsvToBean<ReportAccount> csvToBean = new ModifiedCsvToBean<ReportAccount>();
    MappingStrategy<ReportAccount> mappingStrategy =
        new AnnotationBasedMappingStrategy<ReportAccount>(ReportAccount.class);

    runnableProcessorOnMemory = new RunnableProcessorOnMemory<ReportAccount>(456L, null, null,
        csvToBean, mappingStrategy, ReportDefinitionDateRangeType.CUSTOM_DATE,
        "20140101", "20140131", "123", mockedEntitiesPersister, 5);

    MockitoAnnotations.initMocks(this);

    runnableProcessorOnMemory.setPersister(mockedEntitiesPersister);

    // Load CSV into memory and compress it
    FileInputStream fis = new FileInputStream(CSV_FILE_PATH);
    ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
    GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
    FileUtil.copy(fis, gzipOut);
    gzipOut.flush();
    gzipOut.close();
    doReturn(new ByteArrayInputStream(baos.toByteArray())).when(
        runnableProcessorOnMemory).getReportInputStream();
  }

  @Test
  public void testRun() {
    runnableProcessorOnMemory.run();
    verify(runnableProcessorOnMemory, times(1)).run();
    verify(mockedEntitiesPersister, times(2)).persistReportEntities(
        reportEntitiesCaptor.capture());
  }
}
