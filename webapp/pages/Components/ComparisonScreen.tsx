import React, { useState } from 'react';
import SchoolCard, { SchoolInfo } from './SchoolCard'; // Import SchoolCard component and SchoolInfo type
import { Checkbox, FormControlLabel } from '@mui/material';
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

const SchoolListComponent: React.FC = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [schoolData, setSchoolData] = useState<SchoolInfo[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [options, setOptions] = useState<Options>({
    graduationRate: true,
    expendituresPerStudent: true,
    studentToFacultyRatio: true,
    avgTeacherEducationLevel: true,
  });

  const handleSearch = async () => {
    try {
      const response = await fetch(`/api/DB?schoolName=${searchQuery}`);
      if (response.ok) {
        const data = await response.json();
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

  return (
    <div style={{ background: '#ccc', padding: '10px',height: '100vh', }}>
      <div style={{ display: 'flex' }}>
        <input
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          onKeyPress={handleKeyPress}
          placeholder="Search for a school"
          style={{ flex: 1, padding: '8px' }}
        />
        <button onClick={handleSearch} style={{  ...buttonStyle,marginLeft: '8px' }}>Search</button>
      </div>

      {error && <div>{error}</div>}

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
