import React from 'react';
import TitleWithPercentileCircle from './TitleWithPercentageCircle';

interface GridWithPercentileCirclesProps {
  dropoutRate?: number;
  expendituresPerStudent?: number;
  studentToFacultyRatio?: number;
  avgTeacherEducationLevel?: number;
  avgTeacherExperience?: number;
  collegeBound?: number;
  lowIncome?: number;
  facultyDegree?: number;
  avgTeacherSalary? : number;
}

const GridWithPercentileCircles: React.FC<GridWithPercentileCirclesProps> = ({
  dropoutRate,
  expendituresPerStudent,
  studentToFacultyRatio,
  avgTeacherEducationLevel,
  collegeBound,
  lowIncome,
  facultyDegree,
  avgTeacherExperience,
  avgTeacherSalary
}) => {
  
  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
      {dropoutRate !== undefined && <TitleWithPercentileCircle title="Dropout Rate" percentile={dropoutRate} />}
      {expendituresPerStudent !== undefined && <TitleWithPercentileCircle title="Expenditures Per Student" percentile={expendituresPerStudent} />}
      {collegeBound !== undefined && <TitleWithPercentileCircle title="Percent College Bound" percentile={collegeBound} />}
      {lowIncome !== undefined && <TitleWithPercentileCircle title="Percent Low Income" percentile={lowIncome} />}

      {studentToFacultyRatio !== undefined && <TitleWithPercentileCircle title="Student to Faculty Ratio" percentile={studentToFacultyRatio} />}
      {avgTeacherEducationLevel !== undefined && <TitleWithPercentileCircle title="Avg Teacher Education Level" percentile={avgTeacherEducationLevel} />}
      {facultyDegree !== undefined && <TitleWithPercentileCircle title="Avg Faculty Degree" percentile={facultyDegree} />}
      {avgTeacherExperience !== undefined && <TitleWithPercentileCircle title="Avg Faculty Experience" percentile={avgTeacherExperience} />}
      {avgTeacherSalary !== undefined && <TitleWithPercentileCircle title="Avg Faculty Salary" percentile={avgTeacherSalary} />}
    </div>
  );
};

export default GridWithPercentileCircles;
