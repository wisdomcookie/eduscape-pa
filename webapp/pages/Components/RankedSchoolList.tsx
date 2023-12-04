import React, { useEffect, useState } from 'react';
import SchoolCard, { SchoolInfo } from './SchoolCard'; // Import SchoolCard component and SchoolInfo type
import { Checkbox, FormControlLabel, Typography } from '@mui/material';
import SortableListContainer from './RankedList';
import KeyRow from './keyRow';
import { Options } from './ComparisonScreen';
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



const RankedSchoolList: React.FC = () => {
  const [sortedItems, setSortedItems] = useState([
        'dropoutRate',
        'percentLowIncome',
        'collegeBound',
        'spendingPerStudent',
        'studentToFacultyRatio',
        'avgTeacherDegreeLevel',
        'avgTeacherExperience',
        'avgTeacherSalary'
  ]);
  const [schoolData, setSchoolData] = useState<SchoolInfo[]>([]);
  const [options, setOptions] = useState<Options>({
    dropoutRate: true,
    percentLowIncome: true,
    collegeBound: true,
    spendingPerStudent: true,
    studentToFacultyRatio: true,
    avgTeacherExperience: true,
    avgTeacherDegreeLevel: true,
    avgTeacherSalary: true
  });

  const handleOptionChange = (option: keyof Options) => {
    setOptions(prevOptions => ({
      ...prevOptions,
      [option]: !prevOptions[option]
    }));
  };

  const fetchSchoolData = async () => {
    try {
      const param = sortedItems.map(item => `CASE WHEN ${item} = -1 THEN 1 ELSE ${item} END DESC`).join(', ');
      console.log(param)
      const response = await fetch(`http://localhost:8080/schools/ranking?n=25&order=ASC&type=${sortedItems[0]}`);

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      console.log(data)
      setSchoolData(data);
    } catch (error) {
      console.error('Error fetching school data:', error);
    }
  };


  useEffect(() => {
    fetchSchoolData()
  }, []);

  

  return (
    <div style={{ display: 'flex', height: '100%', backgroundColor: '#F2E3DB', paddingBottom: '200px' }}>

      <div style={{ padding: '10px', flex: 3, textAlign: 'center' }}>
      <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Oleo+Script:wght@400&display=swap" />
      <Typography variant="h2" style={{  fontFamily: 'Oleo Script, cursive', }}>Top 25</Typography>
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
              checked={options.studentToFacultyRatio}
              onChange={() => handleOptionChange('studentToFacultyRatio')}
            />
          }
          label="Student To Faculty Ratio"
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
        <FormControlLabel
          control={
            <Checkbox
              checked={options.avgTeacherSalary}
              onChange={() => handleOptionChange('avgTeacherSalary')}
            />
          }
          label="Average Teacher Salary"
        />
      </div>
        <div>
      <KeyRow></KeyRow>
      </div>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(40%, 1fr))', gap: '20px', marginTop: '20px' }}>
        {schoolData.map((school, index) => (
          <SchoolCard key={index} schoolInfo={school} options={options} />
        ))}
      </div>
      </div>

      {/* Right side */}
      <div style={{ flex: 1, padding: '10px',position: 'sticky' ,  height: '100%' }}>
        <div >
        <SortableListContainer sortedItems={sortedItems} setSortedItems={setSortedItems} handleClicked = {fetchSchoolData} />
        </div>
      </div>
    </div>
  );
};

export default RankedSchoolList;
