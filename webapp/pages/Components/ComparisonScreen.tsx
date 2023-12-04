import React, { useEffect, useState } from 'react';
import SchoolCard, { SchoolInfo } from './SchoolCard'; // Import SchoolCard component and SchoolInfo type
import { Checkbox, FormControlLabel, Typography } from '@mui/material';
import KeyRow from './keyRow';
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
  

  export interface Options {
    dropoutRate: boolean;
    percentLowIncome: boolean;
    collegeBound: boolean;
    spendingPerStudent: boolean;
    studentToFacultyRatio: boolean;
    avgTeacherExperience: boolean;
    avgTeacherDegreeLevel: boolean;
    avgTeacherSalary: boolean
  }

const SchoolListComponent: React.FC = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [schoolData, setSchoolData] = useState<SchoolInfo[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [options, setOptions] = useState<Options>({
    dropoutRate: true,
    percentLowIncome: true,
    collegeBound: true,
    spendingPerStudent: true,
    studentToFacultyRatio: true,
    avgTeacherExperience: true,
    avgTeacherDegreeLevel: true,
    avgTeacherSalary: true,
  });
  const [schoolNames, setSchoolNames] = useState<string[]>([]);

  const handleSearch = async () => {
    try {
      
      const response = await fetch(`http://localhost:8080/schools/rates?name=${searchQuery}`);
      console.log(response)
      if (response.ok) {
        const data = await response.json();
        console.log(data)
        setSchoolData(prevData => [...prevData, data]);
        setError(null);
      } else {
        setError('School not found');
      }
    } catch (error) {
      console.error('Error fetching data:', error);
      setError('Error fetching data');
    }
  };

  const handleClear = () => {
    setSchoolData([]);
  };

  const handleKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      handleSearch();
    }
  };
  const handleOptionChange = (option: keyof Options) => {
    setOptions(prevOptions => ({
      ...prevOptions,
      [option]: !prevOptions[option]
    }));
  };

  
  useEffect(() => {
    const fetchSchoolNames = async () => {
      try {
        console.log('Fetching school names...');
        const response = await fetch(`http://localhost:8080/schools/allNames`);
        console.log(response)
        if (response.ok) {
          const data = await response.json();
          console.log(data)
          setSchoolNames(data);
        }
        else{
          console.log(response.headers)

        }
      } catch (error) {
        console.error('Error fetching school names:', error);
      }
    };

    fetchSchoolNames();
    
  }, []);

  useEffect(() => {
    console.log( "Schol names " + schoolNames)
    
  },schoolNames);

  
  
  return (
    <div style={{ padding: '10px',height: 'fit-content', backgroundColor: '#F2E3DB', textAlign: 'center' }}>
      <Typography variant="h2" style={{ marginBottom: '16px',  }}>Search and Compare Schools</Typography>
      
      <div style={{ display: 'flex' }}>
        <input
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          onKeyPress={handleKeyPress}
          placeholder="Search for a school"
          style={{ flex: 1, padding: '8px' }}
          list="schoolNames"
        />
        <button onClick={handleSearch} style={{  ...buttonStyle,marginLeft: '8px' }}>Search</button>
      </div>
      <datalist id="schoolNames">
        {schoolNames.map((name, index) => (
          <option key={index} value={name} />
        ))}
      </datalist>


      {error && <div>{error}</div>}
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

      {schoolData.length > 1 && (
        <div style={{ display: 'flex', justifyContent: 'center' }}>
          <button onClick={handleClear} style={{ ...buttonStyle, marginTop: '10px' }} disabled={schoolData.length === 0}>
            Clear All
          </button>
        </div>
      )}
    </div>
  );
};

export default SchoolListComponent;
