import React, { useState } from 'react';
import { applyLeave } from '../../service/timesheet/LeaveService';
import { getLoggedInUser } from '../../service/auth/AuthService';
import DatePicker from 'react-multi-date-picker';
const LeaveForm = ({ refreshLeaves }) => {
  const [leaveType, setLeaveType] = useState('');
  const [leaveDuration, setLeaveDuration] = useState(0);
  const [leaveDates, setLeaveDates] = useState([]);
  const [notes, setNotes] = useState('');
  const [userName] = useState(getLoggedInUser()); // Static username for example

  const handleSubmit = async (e) => {
    e.preventDefault();  // Prevents the default form submission behavior

    // Create leave data object
    const leaveDto = {
      userName: userName,  // Correct variable name
      leaveType: leaveType,
      leaveDuration: leaveDuration,
      date: leaveDates.map(date=>date.format('YYYY-MM-DD')),  // Splitting comma-separated dates into an array
    };

    try {
      // Call the service to submit leave data
      console.log("Submitting leave form", leaveDto);  // Useful for debugging
      await applyLeave(leaveDto);
      alert('Leave applied successfully!');
      refreshLeaves(); // Optional: refresh the leave dashboard
    } catch (error) {
      console.error('Error applying leave', error);
      alert('Failed to apply leave');
    }
  };

  return (
    <div>
      <h2>Apply for Leave</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Leave Type: </label>
          <select value={leaveType} onChange={(e) => setLeaveType(e.target.value)} required>
            <option value="">Select Leave Type</option>
            <option value="Sick_Leave">Sick Leave</option>
            <option value="Casual_Leave">Casual Leave</option>
            <option value="Earned_Leave">Earned Leave</option>
          </select>
        </div>

        <div>
          <label>Leave Duration (hours per day): </label>
          <input
            type="number"
            value={leaveDuration}
            onChange={(e) => setLeaveDuration(e.target.value)}
            min="1"
            max="8"
            required
          />
        </div>

        <div>
          <label>Leave Dates (comma-separated): </label>
          <DatePicker
            multiple
            value={leaveDates}
            onChange={setLeaveDates} // Store selected dates
            format="YYYY-MM-DD"
            placeholder="Select leave dates"
            required
          />
        </div>

        <div>
          <label>Notes: </label>
          <textarea
            value={notes}
            onChange={(e) => setNotes(e.target.value)}
            placeholder="Additional information (optional)"
          ></textarea>
        </div>

        {/* No need for onClick on button, form submission is handled by onSubmit */}
        <button type="submit">Submit Leave</button>
      </form>
    </div>
  );
};

export default LeaveForm;
