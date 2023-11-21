# API Documentation
Similar documentation is available in the code (DataManager and Controller classes)

## Percentile Rankings
An explanation of how percentile rankings are calculated
- All "percentile" values will be within the range of [0,1]. Multiplying by 100 will give the true percentile
- Values seen as better will have a lower percentile

### Schools
Unfortunately, some schools have more grades than high school (9-12), so the data will not be correct. There's not much we can do about it
- `college_bound` - Sorted by `college_bound/total_enrollment`
  - Sorts by college rate (higher is better)
- `dropout_count` - Sorted by `dropout_count/total_enrollment`
  - Sorts by dropout rate (lower is better)
- `graduate_count` - Sorted by `graduate_count/total_enrollment`
  - Sorts by graduation rate (higher is better)
- `low_income_enrollment` - Sorted by `low_income_enrollment/total_enrollment`
  - Sorts by low income rate (higher is better)
- `total_enrollment` Sorted by its own value.
  - Higher enrollment is seen as better

### Districts
There is no way to accurately determine information in regard to student counts for a district. That is why some of these remain blank
- `average_degree` - Teacher education level (higher is better)
- `average_experience` - Teacher experience level (higher is better)
- `average_salary` - Average teacher salary (higher is better)
- `expenditures` - N/A
- `professional_personnel` - N/A
- `revenue` - N/A