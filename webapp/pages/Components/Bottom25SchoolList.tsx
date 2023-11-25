import React, { useState } from 'react';
import SchoolCard, { SchoolInfo } from './SchoolCard'; // Import SchoolCard component and SchoolInfo type
import { Checkbox, FormControlLabel, Typography } from '@mui/material';
import SortableListContainer from './RankedList';
 // Import SortableListContainer component

const buttonStyle = {
  padding: '10px 20px',
  borderRadius: '10px',
  border: 'none',
  background: '#007bff',
  color: 'white',
  cursor: 'pointer',
  boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.1)',
  marginRight: '8px',
};

interface Options {
  graduationRate: boolean;
  expendituresPerStudent: boolean;
  studentToFacultyRatio: boolean;
  avgTeacherEducationLevel: boolean;
}

const Bottom25SchoolList: React.FC = () => {
  const [sortedItems, setSortedItems] = useState([
        'name',
        'graduationRate',
        'dropoutRate',
        'spendingPerStudent',
        'facultyToStudentRatio',
        'avgTeacherEducationLevel',
        'overall_rating',
  ]);
  const [schoolData, setSchoolData] = useState<SchoolInfo[]>([]);
  const [options, setOptions] = useState<Options>({
    graduationRate: true,
    expendituresPerStudent: true,
    studentToFacultyRatio: true,
    avgTeacherEducationLevel: true,
  });

  const handleOptionChange = (option: keyof Options) => {
    setOptions(prevOptions => ({
      ...prevOptions,
      [option]: !prevOptions[option]
    }));
  };

  const fetchSchoolData = async () => {
    try {
        console.log('fetchSchoolData called');
      const response = await fetch('/api/DB?bottom25=true', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ options, sortedItems }), // Include sortedItems in the request
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      setSchoolData(data);
    } catch (error) {
      console.error('Error fetching school data:', error);
    }
  };

  fetchSchoolData()

  return (
    <div style={{ display: 'flex', height: '100vh', backgroundColor: '#F2E3DB', paddingBottom: '200px',overflow: 'auto' }}>

      <div style={{ padding: '10px', flex: 3, textAlign: 'center' }}>
      <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Oleo+Script:wght@400&display=swap" />
      <Typography variant="h2" style={{ fontFamily: 'Oleo Script, cursive',marginBottom: '16px',  }}>Bottom 25</Typography>
        <div style={{ marginTop: '20px' }}>
          <FormControlLabel
            control={
              <Checkbox
                checked={options.graduationRate}
                onChange={() => handleOptionChange('graduationRate')}
              />
            }
            label="Graduation Rate"
          />
          <FormControlLabel
            control={
              <Checkbox
                checked={options.expendituresPerStudent}
                onChange={() => handleOptionChange('expendituresPerStudent')}
              />
            }
            label="Expenditures Per Student"
          />
          <FormControlLabel
            control={
              <Checkbox
                checked={options.studentToFacultyRatio}
                onChange={() => handleOptionChange('studentToFacultyRatio')}
              />
            }
            label="Student to Faculty Ratio"
          />
          <FormControlLabel
            control={
              <Checkbox
                checked={options.avgTeacherEducationLevel}
                onChange={() => handleOptionChange('avgTeacherEducationLevel')}
              />
            }
            label="Avg Teacher Education Level"
          />
        </div>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(40%, 1fr))', gap: '20px', marginTop: '20px' }}>
        {schoolData.map((school, index) => (
          <SchoolCard key={index} schoolInfo={school} />
        ))}
      </div>
      </div>

      {/* Right side */}
      <div style={{ flex: 1, padding: '10px', position: 'sticky', top: '0'  }}>
        <SortableListContainer sortedItems={sortedItems} setSortedItems={setSortedItems} handleClicked = {fetchSchoolData} />
      </div>
    </div>
  );
};

export default Bottom25SchoolList;
