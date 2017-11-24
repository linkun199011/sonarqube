/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.db.measure;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.Rule;
import org.junit.Test;
import org.sonar.api.utils.System2;
import org.sonar.db.DbTester;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class LiveMeasureDaoTest {

  private static final int A_METRIC_ID = 42;

  @Rule
  public DbTester db = DbTester.create(System2.INSTANCE);

  private LiveMeasureDao underTest = db.getDbClient().liveMeasureDao();

  @Test
  public void insert_and_selectByComponentUuids() {
    LiveMeasureDto measure1 = MeasureTesting.newLiveMeasure().setMetricId(A_METRIC_ID);
    LiveMeasureDto measure2 = MeasureTesting.newLiveMeasure().setMetricId(A_METRIC_ID);
    underTest.insert(db.getSession(), measure1);
    underTest.insert(db.getSession(), measure2);

    List<LiveMeasureDto> selected = underTest.selectByComponentUuids(db.getSession(), asList(measure1.getComponentUuid(), measure2.getComponentUuid()), asList(A_METRIC_ID));
    assertThat(selected)
      .extracting(LiveMeasureDto::getUuid, LiveMeasureDto::getComponentUuid, LiveMeasureDto::getProjectUuid, LiveMeasureDto::getValue, LiveMeasureDto::getDataAsString)
      .containsExactlyInAnyOrder(
        Tuple.tuple(measure1.getUuid(), measure1.getComponentUuid(), measure1.getProjectUuid(), measure1.getValue(), measure1.getDataAsString()),
        Tuple.tuple(measure2.getUuid(), measure2.getComponentUuid(), measure2.getProjectUuid(), measure2.getValue(), measure2.getDataAsString()));
  }

  @Test
  public void selectByComponentUuids_returns_empty_list_if_metric_does_not_match() {
    LiveMeasureDto measure = MeasureTesting.newLiveMeasure().setMetricId(10);
    underTest.insert(db.getSession(), measure);

    List<LiveMeasureDto> selected = underTest.selectByComponentUuids(db.getSession(), asList(measure.getComponentUuid()), asList(222));

    assertThat(selected).isEmpty();
  }

  @Test
  public void selectByComponentUuids_returns_empty_list_if_component_does_not_match() {
    LiveMeasureDto measure = MeasureTesting.newLiveMeasure();
    underTest.insert(db.getSession(), measure);

    List<LiveMeasureDto> selected = underTest.selectByComponentUuids(db.getSession(), asList("_missing_"), asList(measure.getMetricId()));

    assertThat(selected).isEmpty();
  }
}
