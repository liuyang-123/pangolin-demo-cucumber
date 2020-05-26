Feature: When I go to the Google search page, and search for an item, I expect to see some reference to that item in the result summary.
Scenario:
	Given I have gone to the Google page
	When I add agiletestware to the search box
	And click the Search Button
	Then agiletestware should be mentioned in the results