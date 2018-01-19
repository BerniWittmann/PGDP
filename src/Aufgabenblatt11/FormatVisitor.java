/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabenblatt11;


/**
 * @author finnr
 */
public class FormatVisitor extends Visitor {
    private String programString = "";

    public String getProgram() {
        return programString;
    }

    @Override
    public void visit(Number number) {
        programString = programString + number.toString();
    }

    @Override
    public void visit(Variable nameExpression) {
        programString = programString + nameExpression.toString();
    }

    @Override
    public void visit(Unary unary) {
        programString = programString + unary.toString();
    }

    @Override
    public void visit(Binary binary) {
        programString = programString + binary.toString();
    }

    @Override
    public void visit(Function function) {
        programString = programString + function.toString();
    }

    @Override
    public void visit(Declaration declaration) {
        programString = programString + declaration.toString();
    }

    @Override
    public void visit(Read read) {
        programString = programString + read.toString();
    }

    @Override
    public void visit(Assignment assignment) {
        programString = programString + assignment.toString();
    }

    @Override
    public void visit(Composite composite) {
        programString = programString + composite.toString();
    }

    @Override
    public void visit(Comparison comparison) {
        programString = programString + comparison.toString();
    }

    @Override
    public void visit(While while_) {
        programString = programString + while_.toString();
    }

    @Override
    public void visit(True true_) {
        programString = programString + true_.toString();
    }

    @Override
    public void visit(False false_) {
        programString = programString + false_.toString();
    }

    @Override
    public void visit(UnaryCondition unaryCondition) {
        programString = programString + unaryCondition.toString();
    }

    @Override
    public void visit(BinaryCondition binaryCondition) {
        programString = programString + binaryCondition.toString();
    }

    @Override
    public void visit(IfThenElse ifThenElse) {
        programString = programString + ifThenElse.toString();
    }

    @Override
    public void visit(Program program) {
        programString = programString + program.toString();
    }

    @Override
    public void visit(IfThen ifThen) {
        programString = programString + ifThen.toString();
    }

    @Override
    public void visit(Call call) {
        programString = programString + call.toString();
    }

    @Override
    public void visit(Return return_) {
        programString = programString + return_.toString();
    }

    @Override
    public void visit(Write write) {
        programString = programString + write.toString();
    }

    @Override
    public void visit(EmptyStatement emptyStatement) {
        programString = programString + emptyStatement.toString();
    }
}