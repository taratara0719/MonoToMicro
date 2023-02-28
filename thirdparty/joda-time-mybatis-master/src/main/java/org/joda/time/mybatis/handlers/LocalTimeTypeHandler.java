//Copyright 2012 Lucas Libraro
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package org.joda.time.mybatis.handlers;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.sql.*;

@MappedTypes(LocalTime.class)
public class LocalTimeTypeHandler implements TypeHandler
{

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
    {

        LocalTime time = (LocalTime) parameter;
        if (time != null)
        {
            DateTime datetime = new DateTime(1970, 1, 1, time.getHourOfDay(), time.getMinuteOfHour(),
                    time.getSecondOfMinute(), 0);
            ps.setTime(i, new Time(datetime.toDate().getTime()));
        }
        else
        {
            ps.setTime(i, null);
        }
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException
    {
        Time time = rs.getTime(columnName);
        if (time != null)
        {
            return new LocalTime(time.getTime());
        }
        else
        {
            return null;
        }

    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        Time time = cs.getTime(columnIndex);
        if (time != null)
        {
            return new LocalTime(time.getTime());
        }
        else
        {
            return null;
        }
    }

}
