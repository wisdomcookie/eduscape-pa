import React, { useState } from 'react';
import SchoolCard, { SchoolInfo } from './SchoolCard'; // Import SchoolCard component and SchoolInfo type
import { Checkbox, FormControlLabel, Typography } from '@mui/material';
import SortableListContainer from './RankedList';
import KeyRow from './keyRow';
import { Options } from './ComparisonScreen';

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
    dropoutRate: true,
    percentLowIncome: true,
    collegeBound: true,
    spendingPerStudent: true,
    facultyToStudentRatio: true,
    avgTeacherExperience: true,
    avgTeacherDegreeLevel: true,
  });

  const handleOptionChange = (option: keyof Options) => {
    setOptions(prevOptions => ({
      ...prevOptions,
      [option]: !prevOptions[option]
    }));
  };

  const fetchSchoolData = async () => {
    try {
        
      const param = sortedItems.join(',');
      const response = await fetch("http://localhost:8080/schools/bottom25?orderByClause=" + param, );

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
      <Typography variant="h2" style={{ fontFamily: 'Oleo Script, cursive'  }}>Bottom 25</Typography>
      <h1 style={{ fontSize: '2em', fontWeight: 'bold', }}>Visability Options</h1>
        <div style={{ marginTop: '20px' }}>
        <FormControlLabel
          control={
            <Checkbox
              checked={options.dropoutRate}
              onChange={() => handleOptionChange('dropoutRate')}
            />
          }
          label="Dropout Rate"
        />
        <FormControlLabel
          control={
            <Checkbox
              checked={options.spendingPerStudent}
              onChange={() => handleOptionChange('spendingPerStudent')}
            />
          }
          label="Spending Per Student"
        />
        <FormControlLabel
          control={
            <Checkbox
              checked={options.facultyToStudentRatio}
              onChange={() => handleOptionChange('facultyToStudentRatio')}
            />
          }
          label="Faculty to Student Ratio"
        />
        <FormControlLabel
          control={
            <Checkbox
              checked={options.avgTeacherDegreeLevel}
              onChange={() => handleOptionChange('avgTeacherDegreeLevel')}
            />
          }
          label="Avg Teacher Education Level"
        />
        <FormControlLabel
          control={
            <Checkbox
              checked={options.avgTeacherExperience}
              onChange={() => handleOptionChange('avgTeacherExperience')}
            />
          }
          label="Avg Teacher Experience (Years)"
        />
        <FormControlLabel
          control={
            <Checkbox
              checked={options.collegeBound}
              onChange={() => handleOptionChange('collegeBound')}
            />
          }
          label="Percent College Bound"
        />
        <FormControlLabel
          control={
            <Checkbox
              checked={options.percentLowIncome}
              onChange={() => handleOptionChange('percentLowIncome')}
            />
          }
          label="Percent Low Income"
        />
        
      </div>
        <div>
      <KeyRow></KeyRow>
      </div>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(40%, 1fr))', gap: '20px', marginTop: '20px' }}>
        {schoolData.map((school, index) => (
          <SchoolCard key={index} schoolInfo={school} options = {options} />
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
