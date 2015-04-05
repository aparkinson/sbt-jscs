/*global process, require */

(function () {

  "use strict";

  var args = process.argv;
  var console = require("console");
  var fs = require("fs");
  var Checker = require('jscs');
  var loadConfigFile = require('jscs/lib/cli-config');

  var SOURCE_FILE_MAPPINGS_ARG = 2;

  var checker = new Checker();
  checker.registerDefaultRules();
  checker.configure(loadConfigFile.load('.jscsrc'));

  var sourceFileMappings = JSON.parse(args[SOURCE_FILE_MAPPINGS_ARG]);
  var sourceFilesToProcess = sourceFileMappings.length;
  var results = [];
  var problems = [];

  sourceFileMappings.forEach(function (sourceFilePath) {
    var sourceFile = sourceFilePath[0];
    fs.readFile(sourceFile, "utf8", function (err, source) {
      if (err) {
        console.error("Error while trying to read " + source, err);
      } else {
        var errors = checker.checkString(source, sourceFile.relative);
        var lines = errors._file.getLines();
        var errorCount = 0;
        errors.getErrorList().forEach(function (violation) {
          if (violation) {
            problems.push({
              message: violation.message,
              severity: "warn",
              lineNumber: violation.line,
              characterOffset: violation.column,
              lineContent: lines[violation.line - 1],
              source: sourceFile
            });
            ++errorCount;
          }
        });
        results.push({
          source: sourceFile,
          result: (errorCount === 0 ? {filesRead: [sourceFile], filesWritten: []} : null)
        });
      }
      if (--sourceFilesToProcess === 0) {
        console.log("\u0010" + JSON.stringify({results: results, problems: problems}));
      }
    });
  });
}());