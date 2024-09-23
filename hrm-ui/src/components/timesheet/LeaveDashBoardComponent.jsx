import React, { useState, useEffect } from 'react';
import { getLeaveBalance, getLeaveDatesPerType } from '../../service/timesheet/LeaveService';

const LeaveDashboard = ({ username }) => {
  const [leaveBalance, setLeaveBalance] = useState({});
  const [leaveDates, setLeaveDates] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const balance = await getLeaveBalance(username);
        const dates = await getLeaveDatesPerType(username);
        setLeaveBalance(balance);
        setLeaveDates(dates);
      } catch (error) {
        console.error('Error fetching leave data', error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [username]);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <h2>Leave Dashboard</h2>
      <table border="1" style={{ width: '100%', textAlign: 'left', borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>Leave Type</th>
            <th>Remaining Days</th>
            <th>Used Dates</th>
          </tr>
        </thead>
        <tbody>
          {Object.keys(leaveBalance).map((leaveType) => (
            <tr key={leaveType}>
              <td>{leaveType}</td>
              <td>{leaveBalance[leaveType]}</td>
              <td>
                <ul style={{ margin: 0, padding: 0, listStyle: 'none' }}>
                  {leaveDates[leaveType] && leaveDates[leaveType].map((date, index) => (
                    <li key={index}>{date}</li>
                  ))}
                </ul>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LeaveDashboard;
