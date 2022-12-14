package jpf.logic;

/**
 * 
 * 
 * @author Matthew Walker
 * @author Franck van Breugel
 */
public class Visitor extends LabelBaseVisitor<Label> {

	/**
	 * Visits the given Initial node in the parse tree and returns the abstract
	 * syntax tree corresponding to the subtree of the parse tree rooted at the
	 * Initial node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the Initial
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitInitial(LabelParser.InitialContext ctx) {
		return new Initial();
	}

	/**
	 * Visits the given End node in the parse tree and returns the abstract syntax
	 * tree corresponding to the subtree of the parse tree rooted at the End node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the End
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitEnd(LabelParser.EndContext ctx) {
		return new End();
	}

	/**
	 * Visits the given BooleanStaticField node in the parse tree and returns the
	 * abstract syntax tree corresponding to the subtree of the parse tree rooted at
	 * the BooleanStaticField node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the
	 *                BooleanStaticField
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitBooleanStaticField(LabelParser.BooleanStaticFieldContext ctx) {
		return new BooleanStaticField(ctx.fieldName().getText(), Boolean.parseBoolean(ctx.booleanValue().getText()));
	}

	/**
	 * Visits the given BooleanLocalVariable node in the parse tree and returns the
	 * abstract syntax tree corresponding to the subtree of the parse tree rooted at
	 * the BooleanLocalVariable node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the
	 *                BooleanLocalVariable
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitBooleanLocalVariable(LabelParser.BooleanLocalVariableContext ctx) {
		return new BooleanLocalVariable(ctx.methodName().getText() + ctx.parameters().getText(), 
				ctx.variableName().getText(),
				Boolean.parseBoolean(ctx.booleanValue().getText()));
	}

	/**
	 * Visits the given InvokedMethod node in the parse tree and returns the
	 * abstract syntax tree corresponding to the subtree of the parse tree rooted at
	 * the InvokedMethod node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the
	 *                InvokedMethod
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitInvokedMethod(LabelParser.InvokedMethodContext ctx) {
		return new InvokedMethod(ctx.methodName().getText(), ctx.parameters().getText());
	}

	/**
	 * Visits the given ReturnedVoidMethod node in the parse tree and returns the
	 * abstract syntax tree corresponding to the subtree of the parse tree rooted at
	 * the ReturnedVoidMethod node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the
	 *                ReturnedVoidMethod
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitReturnedVoidMethod(LabelParser.ReturnedVoidMethodContext ctx) {
		return new ReturnedVoidMethod(ctx.methodName().getText(), ctx.parameters().getText());
	}

	/**
	 * Visits the given ReturnedBooleanMethod node in the parse tree and returns the
	 * abstract syntax tree corresponding to the subtree of the parse tree rooted at
	 * the ReturnedBooleanMethod node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the
	 *                ReturnedBooleanMethod
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitReturnedBooleanMethod(LabelParser.ReturnedBooleanMethodContext ctx) {
		return new ReturnedBooleanMethod(ctx.methodName().getText(),
				ctx.parameters().getText(),
				Boolean.parseBoolean(ctx.booleanValue().getText()));
	}

	/**
	 * Visits the given ThrownException node in the parse tree and returns the
	 * abstract syntax tree corresponding to the subtree of the parse tree rooted at
	 * the ThrownException node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the
	 *                ThrownException
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitThrownException(LabelParser.ThrownExceptionContext ctx) {
		return new ThrownException(ctx.className().getText());
	}

	/**
	 * Visits the given SynchronizedStaticMethod node in the parse tree and returns
	 * the abstract syntax tree corresponding to the subtree of the parse tree
	 * rooted at the SynchronizedStaticMethod node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the
	 *                SynchronizedStaticMethod
	 * @return A {@code Label} instance that represents abstract syntax tree
	 *         corresponding to the label defined by the context
	 */
	@Override
	public Label visitSynchronizedStaticMethod(LabelParser.SynchronizedStaticMethodContext ctx) {
		return new SynchronizedStaticMethod(ctx.methodName().getText(), ctx.parameters().getText());
	}

}
